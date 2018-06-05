package org.artempopov.client.world

import org.apache.logging.log4j.LogManager
import org.artempopov.client.graphics.Drawable
import org.artempopov.client.gui.EngineMain
import org.artempopov.client.net.UpdateListener
import org.artempopov.client.net.getShapesFromResponse
import org.artempopov.serverFirst.proto.ResponseProto

/**
 * This object periodically request server for
 * info about world state and update client model with this info
 *
 * @author Artem Popov
 */
class WorldUpdater: UpdateListener {

    private val LOG = LogManager.getLogger()

    override fun onUpdate(notifyResponse: ResponseProto.NotifyResponse) {
        removeOldShapes()
        val shapes = getShapesFromResponse(notifyResponse)
        addShapesToScene(shapes)
        updateScene()
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
}