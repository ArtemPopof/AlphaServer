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
        val shapes = getShapesFromResponse(notifyResponse)
        updateShapesOnScene(shapes)
        updateScene()
    }

    private fun updateShapesOnScene(shapes: List<Drawable>) {
        for (shape in shapes) {
            EngineMain.updateShapeOnScene(shape)
        }
    }

    private fun updateScene() {
        EngineMain.repaintScene()
    }
}