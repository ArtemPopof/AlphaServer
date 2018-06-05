package org.artempopov.serverFirst.handler

import org.artempopov.common.util.toDto
import org.artempopov.serverFirst.dto.Client
import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.serverFirst.storage.ClientManager
import org.artempopov.serverFirst.util.createEmptyResponse
import org.artempopov.serverFirst.util.createErrorResponse
import java.awt.Point
import java.awt.Rectangle

const val MOVE_SPEED = 5
const val START_POINT_X = 0
const val START_POINT_Y = 0

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
     * Handle move request
     */
    fun handleMove(request: RequestProto.Request): ByteArray {
        validateRequest(request)

        val client: Client = try {
            ClientManager.getClient(request.clientId)
        } catch (e: NoSuchClientException) {
            return createErrorResponse(ResponseProto.ErrorType.UNREGISTERED).toByteArray()
        }

        val oldPosition = client.position

        val newPosition = move(oldPosition, request.moveRequest.direction)
        val isMoveAllowed = checkPositionValid(newPosition)
        if (isMoveAllowed) {
            client.position = newPosition
            WorldNotifier.clientMoved(request.clientId)
        }

        return createEmptyResponse().toByteArray()
    }

    private fun validateRequest(request: RequestProto.Request) {
        if (!request.hasClientId()) {
            throw InvalidRequestException("Client id field must be filled")
        }
        if (!request.hasMoveRequest()) {
            throw InvalidRequestException("Move request field must be not empty")
        }
    }

    private fun move(clientPosition: Point, direction: RequestProto.MoveDirection): Point {
        val newPosition = Point(clientPosition)

        when (direction) {
            RequestProto.MoveDirection.DOWN -> newPosition.y += MOVE_SPEED
            RequestProto.MoveDirection.UP -> newPosition.y -= MOVE_SPEED
            RequestProto.MoveDirection.LEFT -> newPosition.x -= MOVE_SPEED
            RequestProto.MoveDirection.RIGHT -> newPosition.x += MOVE_SPEED
        }

        return newPosition
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

}