package org.artempopov.ServerFirst.handler

import org.artempopov.ServerFirst.dto.ShapeColor
import org.artempopov.ServerFirst.dto.ShapeType
import org.artempopov.ServerFirst.proto.RequestProto
import org.artempopov.ServerFirst.proto.ResponseProto

/**
 * Handles registration/unregistration related requests
 *
 * @author Artem Popov
 */
object RegistrationHandler {

    private var lastClientId: Long = 0

    /**
     * Perform registration
     */
    fun handleRegistration(request: RequestProto.Request): ByteArray {
        validateRequest(request)

        val color = ShapeColor.valueOf(request.registrationRequest.color.name)
        val shape = ShapeType.valueOf(request.registrationRequest.shape.name)

        registerInOtherHandlers(color, shape)

        return createGoodResponse().toByteArray()
    }

    private fun validateRequest(request: RequestProto.Request) {
        if (!request.hasRegistrationRequest()) {
            throw InvalidRequestException("Move request field must be not empty")
        }
    }

    private fun registerInOtherHandlers(color: ShapeColor, shape: ShapeType) {
        MoveHandler.registerClient(lastClientId)
        ShapeHandler.registerClient(lastClientId, shape)
        //ColorHandler.registerClient(lastClientId, color)
    }

    private fun createGoodResponse(): ResponseProto.Response {
        val response = ResponseProto.Response.newBuilder()

        response.registration = createRegistrationMessage()

        return response.build()
    }

    private fun createRegistrationMessage(): ResponseProto.RegistrationResponse? {
        val message = ResponseProto.RegistrationResponse.newBuilder()

        message.clientId = lastClientId++

        return message.build()
    }
}
