package org.artempopov.serverFirst.handler

import org.artempopov.common.util.toDto
import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.util.createEmptyResponse

/**
 * Handle all color change-set requests
 */
object ColorHandler {

    private val clientColors = HashMap<Long, ShapeColor>()

    /**
     * Handle colorChangeRequest
     */
    fun handleColorChange(request: RequestProto.Request): ByteArray {
        validateRequest(request)

        val clientID = request.clientId
        val color = request.changeColorRequest.color

        clientColors[clientID] = toDto(color)

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
     * @param color shape color
     * @throw IllegalArgumentException if client with id already exists
     */
    fun registerClient(id: Long, color: ShapeColor) {
        if (clientColors.containsKey(id)) {
            throw IllegalArgumentException("Client ID must be unique")
        }

        clientColors[id] = color
    }

    fun getAllColors(): HashMap<Long, ShapeColor> {
        return clientColors
    }
}
