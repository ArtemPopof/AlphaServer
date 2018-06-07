package org.artempopov.client.bot

import org.apache.logging.log4j.LogManager
import org.artempopov.client.graphics.Drawable
import org.artempopov.client.net.Channel
import org.artempopov.client.net.UpdateListener
import org.artempopov.client.net.getShapesFromResponse
import org.artempopov.common.util.sleepRemainingTime
import org.artempopov.serverFirst.handler.MOVE_SPEED
import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.proto.ResponseProto
import java.awt.Point
import java.lang.Math.abs
import java.util.*

private const val UPDATE_PERIOD = 1000 / 5L
private const val IDLE_DELAY_MAX = 5000
private const val MAX_WALK_RANGE = 50

private const val X_MAX = 500 - 50
private const val Y_MAX = 500 - 50

/**
 * Class for bot simulation
 */
class Bot(private val channel: Channel): UpdateListener {
    private val LOG = LogManager.getLogger()

    private var position = Point(0, 0)
    private var shapes: List<Drawable> = ArrayList()
    private val moverThread = Thread(createMoverTask(), "BotMover$channel")

    private var state = BotState.IDLE
    private var idleTime = 0L
    private var lastStateTime = System.currentTimeMillis()
    private var goalPosition = Point(0, 0)

    private val random = Random(System.nanoTime())

    init {
        idleTime = getIdleDelay()

        moverThread.start()
    }

    override fun onUpdate(notifyResponse: ResponseProto.NotifyResponse) {
        shapes = getShapesFromResponse(notifyResponse)
    }

    private fun getIdleDelay(): Long {
        return abs(random.nextLong()) % IDLE_DELAY_MAX
    }

    private fun createMoverTask(): Runnable {
        return Runnable{
            var lastTime = 0L
            while (!Thread.currentThread().isInterrupted) {
                LOG.debug("UpdateCycleStart")

                lastTime = System.currentTimeMillis()

                updateBotState()

                sleepRemainingTime(lastTime, UPDATE_PERIOD)

                LOG.debug("BOT STATE: $state")
                LOG.debug("GOAL: $goalPosition")
                LOG.debug("POSITION: $position")
                LOG.debug("Time left: ${System.currentTimeMillis() - lastStateTime}")
            }
        }
    }

    private fun isChangingState(): Boolean {
        return when (state) {
            BotState.IDLE -> isIdleEnough()
            BotState.WALK -> isGoalReached()
        }
    }

    private fun isIdleEnough(): Boolean {
        return System.currentTimeMillis() - lastStateTime >= idleTime
    }

    private fun isGoalReached(): Boolean {
        val xDiff = goalPosition.x - position.x
        val yDiff = goalPosition.y - position.y

        return (abs(xDiff) <= 7 && abs(yDiff) <= 7)
    }

    private fun changeToRandomState() {
        val isIdle = random.nextBoolean()
        if (isIdle) {
            setRandomIdleState()
        } else {
            setRandomWalkState()
        }

        lastStateTime = System.currentTimeMillis()
    }

    private fun setRandomIdleState() {
        idleTime = getIdleDelay()
        state = BotState.IDLE
    }

    private fun setRandomWalkState() {
        goalPosition = getRandomGoal()
        state = BotState.WALK
    }

    private fun getRandomGoal(): Point {
        val xWalkDistance = random.nextInt() % MAX_WALK_RANGE
        val yWalkDistance = random.nextInt() % MAX_WALK_RANGE

        val newGoal = Point(position.x + xWalkDistance, position.y + yWalkDistance)
        return applyRestrictions(newGoal)
    }

    private fun applyRestrictions(point: Point): Point {
        if (point.x > X_MAX) {
            point.x = X_MAX
        } else if (point.x < 0) {
            point.x = 0
        }

        if (point.y > Y_MAX) {
            point.y = Y_MAX
        } else if (point.y < 0) {
            point.y = 0
        }

        return point
    }

    private fun updateBotState() {
        when (state) {
            BotState.IDLE -> idle()
            BotState.WALK -> walk()
        }
    }

    private fun idle() {
        val isChangeState = isChangingState()
        if (isChangeState) {
            changeToRandomState()
            return
        }

        Thread.sleep(idleTime + 1)
    }

    private fun walk() {
        val isChangeState = isChangingState()
        if (isChangeState) {
            changeToRandomState()
            return
        }

        val reachedX = almostEquals(goalPosition.x, position.x)
        val reachedY = almostEquals(goalPosition.y, position.y)

        if (!reachedX) {
            moveOnLineAxis()
            return
        }
        if (!reachedY) {
            moveOnPageAxis()
            return
        }
    }

    private fun almostEquals(first: Int, second: Int): Boolean {
        val diff = abs(first - second)
        return diff <= MOVE_SPEED
    }

    private fun moveOnLineAxis() {
        if (goalPosition.x > position.x) {
            move(RequestProto.MoveDirection.RIGHT)
            position.x += MOVE_SPEED
            return
        }
        if (goalPosition.x < position.x) {
            move(RequestProto.MoveDirection.LEFT)
            position.x -= MOVE_SPEED
            return
        }
    }

    private fun moveOnPageAxis() {
        if (goalPosition.y < position.y) {
            move(RequestProto.MoveDirection.UP)
            position.y -= MOVE_SPEED
            return
        }
        if (goalPosition.y > position.y) {
            move(RequestProto.MoveDirection.DOWN)
            position.y += MOVE_SPEED
            return
        }
    }

    private fun move(direction: RequestProto.MoveDirection) {
        try {
            channel.sendMoveRequest(direction)
        } catch (e: Exception) {
            LOG.error("Move request failed: " + e)
        }
        LOG.debug("Moving $direction")
    }
}
