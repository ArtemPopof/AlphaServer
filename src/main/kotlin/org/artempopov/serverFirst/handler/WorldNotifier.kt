package org.artempopov.serverFirst.handler

import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import org.artempopov.serverFirst.proto.Common
import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.proto.ResponseProto
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

        response.notify = createNotify(shapesInfo)

        return response.build()
    }

    private fun createShapeInfos(positions: HashMap<Long, Point>,
                                 types: HashMap<Long, ShapeType>): List<ResponseProto.ShapeInfo> {
        val shapes = ArrayList<ResponseProto.ShapeInfo>(positions.size)

        var type: ShapeType?
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

    private fun createShapeInfo(type: ShapeType, color: ShapeColor, position: Point): ResponseProto.ShapeInfo {
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
       return when (type) {
           ShapeType.SQUARE -> Common.Shape.SQUARE
           ShapeType.TRIANGLE -> Common.Shape.TRIANGLE
           ShapeType.CIRCLE -> Common.Shape.CIRCLE
       }
    }

    private fun createNotify(shapes: List<ResponseProto.ShapeInfo>): ResponseProto.NotifyResponse {
        val notify = ResponseProto.NotifyResponse.newBuilder()

        notify.addAllShapes(shapes)

        return notify.build()
    }
}