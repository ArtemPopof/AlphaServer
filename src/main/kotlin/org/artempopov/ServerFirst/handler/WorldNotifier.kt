package org.artempopov.ServerFirst.handler

import org.artempopov.ServerFirst.dto.ShapeColor
import org.artempopov.ServerFirst.dto.ShapeType
import org.artempopov.ServerFirst.proto.Common
import org.artempopov.ServerFirst.proto.RequestProto
import org.artempopov.ServerFirst.proto.ResponseProto
import java.awt.Point

/**
 * World notifier send information about world state
 * after notify request
 *
 * @author Artem Popov
 */
object WorldNotifier {

    /**
     * Handle request for world state information
     */
    fun handleNotifyRequest(request: RequestProto.Request): ByteArray {
        val positions = MoveHandler.getAllClientsPosition()
        val shapes = ShapeHandler.getAllShapes()

        return createNotifyResponse(positions, shapes).toByteArray()
    }

    private fun createNotifyResponse(positions: HashMap<Long, Point>,
                                     shapes: HashMap<Long, ShapeType>): ResponseProto.Response {

        val response = ResponseProto.Response.newBuilder()

        val shapesInfo = createShapeInfos(positions, shapes)

        response.notify.shapesList.addAll(0, shapesInfo)

        return response.build()
    }

    private fun createShapeInfos(positions: HashMap<Long, Point>,
                                 types: HashMap<Long, ShapeType>): List<ResponseProto.ShapeInfo> {
        val shapes = ArrayList<ResponseProto.ShapeInfo>(positions.size)

        var type: ShapeType? = null
        for ((key, position) in positions) {
            // check for model unsync state
            type = types[key]
            if (type == null) {
                throw IllegalStateException("Model storages is not in sync")
            }

            shapes.add(createShapeInfo(type, ShapeColor.BLUE, position))
        }

        return shapes
    }

    private fun createShapeInfo(type: ShapeType, blue: ShapeColor, position: Point): ResponseProto.ShapeInfo {
        val shape = ResponseProto.ShapeInfo.newBuilder()

        shape.position = createPosition(position)
        shape.shape = createShapeType(type)
        shape.color = Common.Color.BLUE

        return shape.build()
    }

    private fun createPosition(pos: Point): Common.Position {
        val position = Common.Position.newBuilder()

        position.x = pos.x
        position.y = pos.y

        return position.build()
    }

    private fun createShapeType(type: ShapeType): Common.Shape {
       when (type) {
           ShapeType.SQUARE -> return Common.Shape.SQUARE
           ShapeType.TRIANGLE -> return Common.Shape.TRIANGLE
           ShapeType.CIRCLE -> return Common.Shape.CIRCLE
       }
    }
}