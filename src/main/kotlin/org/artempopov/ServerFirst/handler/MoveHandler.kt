package org.artempopov.ServerFirst.handler

import org.artempopov.ServerFirst.proto.RequestProto
import org.artempopov.ServerFirst.proto.ResponseProto
import org.artempopov.ServerFirst.util.createEmptyResponse
import org.artempopov.ServerFirst.util.createErrorResponse
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
     * Map contains all clients current positions
     */
    private val clientsPosition = HashMap<Long, Point>()

    /**
     * Handle move request
     */
    fun handleMove(request: RequestProto.Request): ByteArray {
        validateRequest(request)

        val clientId = request.clientId
        val oldPosition = clientsPosition[clientId]
        if (oldPosition == null) {
            return createUnregisteredResponse()
        }

        val newPosition = move(oldPosition, request.moveRequest.direction)
        val isMoveAllowed = checkPositionValid(newPosition)
        if (isMoveAllowed) {
            clientsPosition[clientId] = newPosition
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
            RequestProto.MoveDirection.DOWN -> newPosition.y -= MOVE_SPEED
            RequestProto.MoveDirection.UP -> newPosition.y += MOVE_SPEED
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

    private fun createUnregisteredResponse(): ByteArray {
        return createErrorResponse(ResponseProto.ErrorType.UNREGISTERED).toByteArray()
    }

    /**
     * Register new client
     *
     * @param id client id
     * @throw IllegalArgumentException if client with id already exists
     */
    fun registerClient(id: Long) {
        if (clientsPosition.containsKey(id)) {
            throw IllegalArgumentException("Client ID must be unique")
        }

        clientsPosition[id] = Point(START_POINT_X, START_POINT_Y)
    }

    /**
     * Get specified client position
     *
     * @param id client ID
     * @return location of specified player or null if client doesn't exist
     */
    fun getClientPosition(id: Long): Point? {
        return clientsPosition[id]
    }
}