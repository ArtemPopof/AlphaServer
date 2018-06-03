package org.artempopov.client.world

import org.apache.logging.log4j.LogManager
import org.artempopov.serverFirst.proto.Common
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.client.graphics.Drawable
import org.artempopov.client.gui.EngineMain
import org.artempopov.client.net.Connection
import org.artempopov.client.net.getAllShapesFromServer
import org.artempopov.client.shapes.Square
import org.artempopov.serverFirst.proto.RequestProto

const val TAG = "WorldUpdater"
const val UPDATE_CYCLE_PERIOD = 1000 / 24

/**
 * This object periodically request server for
 * info about world state and update client model with this info
 *
 * @author Artem Popov
 */
class WorldUpdater(private val connection: Connection) {

    private val LOG = LogManager.getLogger()

    private val updater = Thread(createUpdateTask())
    private var running = false

    private var moveDirection: RequestProto.MoveDirection? = null

    private fun createUpdateTask(): Runnable {
        return Runnable {
            var lastTime = 0L
            var fpsLastTime = System.currentTimeMillis()
            var fps = 0
            while (running) {
                lastTime = System.currentTimeMillis()

                val shapes = getAllShapesFromServer(connection)
                removeOldShapes()
                addShapesToScene(shapes)
                updateScene()

                sendMoveRequest()

                fps++

                sleepOvertime(System.currentTimeMillis() - lastTime)

                if (System.currentTimeMillis() - fpsLastTime >= 1000) {
                    LOG.info("Current FPS: $fps")
                    fpsLastTime = System.currentTimeMillis()
                    fps = 0
                }
            }
        }
    }

    private fun removeOldShapes() {
        EngineMain.clear()
    }

    private fun addShapesToScene(shapes: List<Drawable>) {
        for (shape in shapes) {
            EngineMain.addToScene(shape)
        }
    }

    private fun updateScene() {
        EngineMain.updateScene()
    }

    private fun sleepOvertime(milisPast: Long) {
        val overTime = UPDATE_CYCLE_PERIOD - milisPast
        if (overTime <= 0) {
            return
        }

        Thread.sleep(overTime)
    }

    private fun sendMoveRequest() {
        if (moveDirection == null) {
            return
        }

        connection.sendMoveRequest(moveDirection as RequestProto.MoveDirection)
    }

    /**
     * Start world updater thread
     */
    fun start() {
        running = true
        updater.start()
    }

    /**
     * Stop world updater thread
     */
    fun stop() {
        running = false
    }

    /**
     * Set current move direction
     */
    fun setMoveDirection(direction: RequestProto.MoveDirection?) {
        moveDirection = direction
    }
}