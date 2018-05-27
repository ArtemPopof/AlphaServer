package org.artempopov.serverFirst.handler

import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.serverFirst.util.createEmptyResponse
import org.artempopov.serverFirst.util.createErrorResponse
import java.awt.Point
import java.awt.Rectangle

const val MOVE_SPEED = 5

/**
 * Handle move requests
 *
 * @author Artem Popov
 */
object MoveHandler {

    /**
     * By now map geometry resides only in this property
     */
    private val mapRect = Rectangle(500, 500)

    /**
     * Map contains all clients current positions
     */
    val clientsPosition = HashMap<Long, Point>()

    /**
     * Handle move request
     */
    fun handleMove(request: RequestProto.Request): ByteArray {
        validateRequest(request)

        val clientId = request.clientId
        val clientPosition = clientsPosition[clientId]
        if (clientPosition == null) {
            return createUnregisteredResponse()
        }

        val newPosition = move(clientPosition, request.moveRequest.direction)
        val isMoveAllowed = checkPositionValid(newPosition)
        if (isMoveAllowed) {
            clientsPosition[clientId] = oldPosition
        }

        return createEmptyResponse().toByteArray()
    }

    private fun move(clientPosition: Point, direction: RequestProto.MoveDirection): Point {
        when (direction) {
            RequestProto.MoveDirection.DOWN -> clientPosition.y -= MOVE_SPEED
            RequestProto.MoveDirection.UP -> clientPosition.y += MOVE_SPEED
            RequestProto.MoveDirection.LEFT -> clientPosition.x -= MOVE_SPEED
            RequestProto.MoveDirection.RIGHT -> clientPosition.x += MOVE_SPEED
        }

        return clientPosition
    }

    private fun checkPositionValid(newPosition: Point): Boolean {
        if (newPosition.x > mapRect.width || newPosition.x < 0) {
            return false
        }
        if (newPosition.y > mapRect.height || newPosition.y < 0) {
            return false
        }

        return true
    }

    private fun validateRequest(request: RequestProto.Request) {
        if (!request.hasClientId()) {
            throw InvalidRequestException("Client id field must be filled")
        }
        if (!request.hasMoveRequest()) {
            throw InvalidRequestException("Move request field must be not empty")
        }
    }

    private fun createUnregisteredResponse(): ByteArray {
        return createErrorResponse(ResponseProto.ErrorType.UNREGISTERED).toByteArray()
    }
}