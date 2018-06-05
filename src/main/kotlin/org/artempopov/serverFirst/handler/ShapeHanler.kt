package org.artempopov.serverFirst.handler

import org.artempopov.common.util.toDto
import org.artempopov.serverFirst.dto.ShapeType
import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.serverFirst.storage.ClientManager
import org.artempopov.serverFirst.util.createEmptyResponse
import org.artempopov.serverFirst.util.createErrorResponse

/**
 * Handles shape changing requests
 *
 * @author Artem Popov
 */
object ShapeHandler {

    /**
     * Handle shape changing request
     */
    fun handleShapeChange(request: RequestProto.Request): ByteArray {
        validateRequest(request)

        try {
            ClientManager.getClient(request.clientId).shape = toDto(request.changeShapeRequest.shape)
        } catch (e: NoSuchClientException) {
            return createErrorResponse(ResponseProto.ErrorType.UNREGISTERED).toByteArray()
        }

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

}
