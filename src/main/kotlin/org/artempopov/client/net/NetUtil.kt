package org.artempopov.client.net

import org.apache.logging.log4j.LogManager
import org.artempopov.client.shapes.Circle
import org.artempopov.client.shapes.Shape
import org.artempopov.client.shapes.Square
import org.artempopov.client.shapes.Triangle
import org.artempopov.common.util.fromDto
import org.artempopov.common.util.fromProto
import org.artempopov.common.util.toDto
import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.proto.Common
import org.artempopov.serverFirst.proto.ResponseProto
import java.awt.Point

private val LOG = LogManager.getLogger()

/**
 * Request shape info from server for all shapes
 */
fun getAllShapesFromServer(connection: Connection): List<Shape> {
    val notifyResponse = connection.sendNotifyRequest()
    return getShapesFromResponse(notifyResponse)
}

private fun getShapesFromResponse(response: ResponseProto.NotifyResponse): List<Shape> {
    val shapes = ArrayList<Shape>(response.shapesCount)

    val protoShapes = response.shapesList
    for (protoShape in protoShapes) {
        shapes.add(toDtoShape(protoShape))
    }

    return shapes
}

private fun toDtoShape(protoShape: ResponseProto.ShapeInfo): Shape {
    return when (protoShape.shape) {
        Common.Shape.TRIANGLE -> Triangle(getColor(protoShape.color), fromProto(protoShape.position))
        Common.Shape.CIRCLE -> Circle(getColor(protoShape.color), fromProto(protoShape.position))
        Common.Shape.SQUARE -> Square(getColor(protoShape.color), fromProto(protoShape.position))
        null -> throw IllegalArgumentException("shape is null!")
    }
}

private fun getColor(color: Common.Color): ShapeColor {
    return toDto(color)
}
