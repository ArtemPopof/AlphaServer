package org.artempopov.client.registration

import org.apache.logging.log4j.LogManager
import org.artempopov.client.net.NetworkManager
import org.artempopov.common.net.REQUEST_PACKET_VERSION
import org.artempopov.common.util.fromDto
import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import org.artempopov.serverFirst.proto.RequestProto

private const val LOG_TAG = "RegistrationManager"
/**
 * Handle registration by client side
 */
object RegistrationManager {

    private val LOG = LogManager.getLogger()

    /**
     * Perform registration
     *
     * @param shape Shape type
     * @param color Shape color
     *
     * @throws RegistrationException exception in registration process
     */
    fun register(shape: ShapeType, color: ShapeColor) {
        val request = createRequest(shape, color)

        val response = try {
            NetworkManager.sendRegistrationRequest(request)
        } catch (e: Exception) {
            LOG.error(LOG_TAG, e.message)
            throw RegistrationException()
        }

        NetworkManager.setClientId(response.clientId)
    }

    private fun createRequest(shape: ShapeType, color: ShapeColor): RequestProto.Request {
        val request = RequestProto.Request.newBuilder()

        request.type = RequestProto.RequestType.REGISTRATION
        request.registrationRequest = createRegistrationRequest(shape, color)
        request.requestPacketVersion = REQUEST_PACKET_VERSION

        return request.build()
    }

    private fun createRegistrationRequest(shape: ShapeType, color: ShapeColor): RequestProto.RegistrationRequest {
        val request = RequestProto.RegistrationRequest.newBuilder()

        request.color = fromDto(color)
        request.shape = fromDto(shape)

        return request.build()
    }
}