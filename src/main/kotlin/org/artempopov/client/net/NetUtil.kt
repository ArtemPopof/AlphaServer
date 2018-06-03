package org.artempopov.client.net

import org.apache.logging.log4j.LogManager
import org.artempopov.client.graphics.Drawable
import org.artempopov.client.shapes.Square
import org.artempopov.client.world.TAG
import org.artempopov.serverFirst.proto.Common
import org.artempopov.serverFirst.proto.ResponseProto

private val LOG = LogManager.getLogger()

/**
 * Request shape info from server for all shapes
 */
fun getAllShapesFromServer(connection: Connection): List<Drawable> {
    val notifyResponse = connection.sendNotifyRequest()
    return getShapesFromResponse(notifyResponse)
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
