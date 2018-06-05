package org.artempopov.client.net

import org.apache.logging.log4j.LogManager
import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.common.net.readSocketData
import java.io.BufferedOutputStream
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket

const val PACKET_VERSION = 0x101
// request timeout in ms
const val REQUEST_TIMEOUT = 100

/**
 * One channel with server. Contains two sockets,
 * first listen to server update packets and second used to send
 * event requests to server.
 *
 * Common practice to use 1 channel per server.
 */
class Channel(private val host: String, private val port: Int) {
    private val LOG = LogManager.getLogger()

    private val listeners = ArrayList<UpdateListener>(1)
    private var listenThread = Thread(createListenTask(), "UpdateListener")
    private var listenSocket = createServerSocket()
    private var requestSocket = connectToServer()
    var clientId: Long? = null

    init {
        listenThread.start()
    }

    private fun createListenTask(): Runnable {
        return Runnable{
            while (!Thread.currentThread().isInterrupted) {
                val socket = listenSocket.accept()
                val rawData = readSocketData(socket)
                val notifyResponse = ResponseProto.Response.parseFrom(rawData)
                val valid = validateNotifyPacket(notifyResponse)
                if (valid) {
                    notifyListeners(notifyResponse.notify)
                }
            }
        }
    }

    private fun validateNotifyPacket(packet: ResponseProto.Response): Boolean {
        if (!packet.hasNotify()) {
            LOG.error("Invalid notify packet. \n Error: ${packet.errorMessage}, Code: ${packet.error}")
            return true
        }

        return false
    }

    private fun notifyListeners(notifyResponse: ResponseProto.NotifyResponse) {
        for (listener in listeners) {
            listener.onUpdate(notifyResponse)
        }
    }

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

    private fun createServerSocket(): ServerSocket {
        return ServerSocket(CLIENT_LISTEN_PORT)
    }

    private fun reconnect() {
        try {
            this.requestSocket = connectToServer()
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
        requestSocket = connectToServer()
        val outStream = BufferedOutputStream(requestSocket.getOutputStream())

        outStream.write(byteArray)
        outStream.flush()
    }

    private fun waitForResponse(): ResponseProto.Response {
        val rawResponse = getRawResponse()
        return ResponseProto.Response.parseFrom(rawResponse)
    }

    private fun getRawResponse(): ByteArray {
        return readSocketData(requestSocket)
    }


//    private fun checkConnection() {
//        if (socketToServer.isClosed || !socketToServer.isConnected) {
//            connectToServer()
//        }
//    }

    /**
     * Send move direction to server
     *
     * @throws NotRegisteredException when client not registered yet
     * @throws ErrorResponseException when error occurred on server side
     * @throws IOException sending problem
     * @throws InvalidResponseException response malformed
     */
    fun sendMoveRequest(moveDirection: RequestProto.MoveDirection) {
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

    override fun toString(): String {
        if (clientId == null) {
            return "not initialized connection"
        } else {
            return (clientId as Long).toString()
        }
    }

    /**
     * Add update packets listener
     */
    fun addUpdateListener(listener: UpdateListener) {
        listeners.add(listener)
    }
}