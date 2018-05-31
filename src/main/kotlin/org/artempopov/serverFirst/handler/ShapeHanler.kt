package org.artempopov.serverFirst.handler

import org.artempopov.serverFirst.dto.ShapeType
import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.serverFirst.util.createEmptyResponse
import org.artempopov.serverFirst.util.createErrorResponse

/**
 * Handles shape changing requests
 *
 * @author Artem Popov
 */
object ShapeHandler {

    /**
     * Shape of all current clients
     */
    private val clientsShape = HashMap<Long, ShapeType>()

    /**
     * Handle shape changing request
     */
    fun handleShapeChange(request: RequestProto.Request): ByteArray {
        validateRequest(request)

        val clientId = request.clientId
        if (!clientsShape.containsKey(clientId)) {
            return createErrorResponse(ResponseProto.ErrorType.UNREGISTERED).toByteArray()
        }

        val shapeType = request.changeShapeRequest.shape
        clientsShape[clientId] = ShapeType.valueOf(shapeType.name)

        return createEmptyResponse().toByteArray()
    }

    private fun validateRequest(request: RequestProto.Request) {
        if (!request.hasClientId()) {
            throw InvalidRequestException("Client id field must be filled")
        }
        if (!request.hasChangeColorRequest()) {
            throw InvalidRequestException("Move request field must be not empty")
        }
    }
    /**
     * Register new client
     *
     * @param id client id
     * @throw IllegalArgumentException if client with id already exists
     */
    fun registerClient(id: Long, shape: ShapeType) {
        if (clientsShape.containsKey(id)) {
            throw IllegalArgumentException("Client ID must be unique")
        }

        clientsShape[id] = shape
    }

    fun getAllShapes(): HashMap<Long, ShapeType> {
        return clientsShape
    }

}
