package org.artempopov.serverFirst.handler

import org.artempopov.common.util.toDto
import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.serverFirst.storage.ClientManager
import org.artempopov.serverFirst.util.createEmptyResponse
import org.artempopov.serverFirst.util.createErrorResponse

/**
 * Handle all color change-set requests
 */
object ColorHandler {

    /**
     * Handle colorChangeRequest
     */
    fun handleColorChange(request: RequestProto.Request): ByteArray {
        validateRequest(request)

        val clientID = request.clientId
        val color = request.changeColorRequest.color

        try {
            ClientManager.getClient(clientID).color = toDto(color)
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
