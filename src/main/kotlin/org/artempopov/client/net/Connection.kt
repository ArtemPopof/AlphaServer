package org.artempopov.client.net

import org.apache.logging.log4j.LogManager
import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.common.net.readSocketData
import java.io.BufferedOutputStream
import java.io.IOException
import java.net.Socket

const val PACKET_VERSION = 0x101
// request timeout in ms
const val REQUEST_TIMEOUT = 100

/**
 * All network specific functions for client
 */
class Connection(private val host: String, private val port: Int) {
    private val LOG = LogManager.getLogger()

    private var socketToServer: Socket = connectToServer()
    private var clientId: Long? = null

    /**
     * Connect to server
     */
    private fun connectToServer(): Socket {
        try {
            return Socket(host, port)
        } catch (e: Exception) {
            throw CannotConnectToServerException(e)
        }
    }

    private fun reconnect() {
        try {
            this.socketToServer = connectToServer()
        } catch (e: Exception) {
            throw CannotConnectToServerException(e)
        }
    }

    /**
     * Send registration request
     *
     * @throws ErrorResponseException when error occurred on server side
     * @throws IOException sending problem
     * @throws InvalidResponseException response malformed
     */
    fun sendRegistrationRequest(request: RequestProto.Request): ResponseProto.RegistrationResponse {
        checkConnection()

        send(request.toByteArray())
        val response = waitForResponse()
        if (!response.hasRegistration()) {
            throw ErrorResponseException(getErrorFromResponse(response))
        }

        return response.registration
    }

    private fun getErrorFromResponse(response: ResponseProto.Response): Error {
        if (!response.hasError()) {
            throw InvalidResponseException()
        }

        return Error(response.errorMessage, response.error)
    }

    /**
     * Send notify request
     *
     * @throws ErrorResponseException when error occurred on server side
     * @throws IOException sending problem
     * @throws InvalidResponseException response malformed
     */
    fun sendNotifyRequest(): ResponseProto.NotifyResponse {
        checkConnection()

        val request = createNotifyRequest()
        send(request)
        val response = waitForResponse()

        if (!response.hasNotify()) {
            throw InvalidResponseException()
        }

        return response.notify
    }

    private fun createNotifyRequest(): ByteArray {
        val request = RequestProto.Request.newBuilder()

        request.type = RequestProto.RequestType.NOTIFY
        request.requestPacketVersion = PACKET_VERSION

        return request.build().toByteArray()
    }

    private fun send(byteArray: ByteArray) {
        val outStream = BufferedOutputStream(socketToServer.getOutputStream())

        outStream.write(byteArray)
        outStream.flush()
        socketToServer.shutdownOutput()
    }

    private fun waitForResponse(): ResponseProto.Response {
        val rawResponse = getRawResponse()
        socketToServer.close()
        return ResponseProto.Response.parseFrom(rawResponse)
    }

    private fun getRawResponse(): ByteArray {
        return readSocketData(socketToServer)
    }

    private fun checkConnection() {
        if (socketToServer.isClosed || !socketToServer.isConnected) {
            connectToServer()
        }
    }

    fun setClientId(clientId: Long) {
        this.clientId = clientId
    }

    /**
     * Send move direction to server
     *
     * @throws NotRegisteredException when client not registered yet
     * @throws ErrorResponseException when error occurred on server side
     * @throws IOException sending problem
     * @throws InvalidResponseException response malformed
     */
    fun sendMoveRequest(moveDirection: RequestProto.MoveDirection) {
        checkConnection()
        checkClientRegistered()

        val request = createRequest(moveDirection)
        send(request.toByteArray())
        val response = waitForResponse()
        checkForErrors(response)
    }

    private fun checkClientRegistered() {
        if (clientId == null) {
            throw NotRegisteredException()
        }
    }

    private fun createRequest(direction: RequestProto.MoveDirection): RequestProto.Request {
        val request = RequestProto.Request.newBuilder()

        request.type = RequestProto.RequestType.MOVE
        request.requestPacketVersion = PACKET_VERSION
        request.clientId = clientId as Long
        request.moveRequest = createMoveRequest(direction)

        return request.build()
    }

    private fun createMoveRequest(direction: RequestProto.MoveDirection): RequestProto.MoveRequest {
        val request = RequestProto.MoveRequest.newBuilder()

        request.direction = direction

        return request.build()
    }

    private fun checkForErrors(response: ResponseProto.Response) {
        if (hasError(response)) {
            throw ErrorResponseException(getError(response))
        }
    }

    private fun hasError(response: ResponseProto.Response): Boolean {
        return response.hasError() || response.hasErrorMessage()
    }

    private fun getError(response: ResponseProto.Response): Error {
        return Error(response.errorMessage, response.error)
    }

}