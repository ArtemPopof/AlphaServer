package org.artempopov.client.world

import org.apache.logging.log4j.LogManager
import org.artempopov.serverFirst.proto.Common
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.client.graphics.Drawable
import org.artempopov.client.gui.MainFrame
import org.artempopov.client.net.NetworkManager
import org.artempopov.client.shapes.Square
import org.artempopov.serverFirst.proto.RequestProto

const val TAG = "WorldUpdater"
const val UPDATE_CYCLE_PERIOD = 1000 / 5

/**
 * This object periodically request server for
 * info about world state and update client model with this info
 *
 * @author Artem Popov
 */
object WorldUpdater {

    private val LOG = LogManager.getLogger()

    private var scene: MainFrame? = null
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

                val notifyResponse = NetworkManager.sendNotifyRequest()
                val shapes = getShapesFromResponse(notifyResponse)
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

    private fun getShapesFromResponse(response: ResponseProto.NotifyResponse): List<Drawable> {
        val shapes = ArrayList<Drawable>(response.shapesCount)

        val protoShapes = response.shapesList
        for (protoShape in protoShapes) {
            shapes.add(toDtoShape(protoShape))
        }

        return shapes
    }

    private fun toDtoShape(protoShape: ResponseProto.ShapeInfo): Drawable {
        if (protoShape.shape != Common.Shape.SQUARE) {
            LOG.error(TAG, "STUB!! Only square shape parser implemented!")
            return Square(0, 0)
        }

        //LOG.error(TAG + "| STUB!! Only position parameter parser implemented!")

        val x = protoShape.position.x
        val y = protoShape.position.y

        return Square(x, y)
    }

    private fun removeOldShapes() {
        scene!!.clear()
    }

    private fun addShapesToScene(shapes: List<Drawable>) {
        if (scene == null) {
            return
        }

        for (shape in shapes) {
            scene!!.addToScene(shape)
        }
    }

    private fun updateScene() {
        scene!!.updateScene()
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

        NetworkManager.sendMoveRequest(moveDirection as RequestProto.MoveDirection)
    }

    /**
     * Set scene, where info about world stored
     */
    fun setScene(scene: MainFrame) {
        this.scene = scene
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