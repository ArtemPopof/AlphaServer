package org.artempopov.serverFirst.handler

import org.artempopov.serverFirst.dto.Client
import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import org.artempopov.serverFirst.net.WorldNotifier
import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.serverFirst.storage.ClientManager
import java.awt.Point

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
    fun handleRegistration(request: RequestProto.Request, clientAddress: String): ByteArray {
        validateRequest(request)

        val color = ShapeColor.valueOf(request.registrationRequest.color.name)
        val shape = ShapeType.valueOf(request.registrationRequest.shape.name)

        val clientId = registerClient(color, shape, clientAddress)

        WorldNotifier.markClientActive(clientId)

        return createGoodResponse().toByteArray()
    }

    private fun validateRequest(request: RequestProto.Request) {
        if (!request.hasRegistrationRequest()) {
            throw InvalidRequestException("Move request field must be not empty")
        }
    }

    private fun registerClient(color: ShapeColor, shape: ShapeType, address: String): Long {
        val client = Client(shape, color, Point(0, 0), address)
        return ClientManager.registerClient(client)
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
