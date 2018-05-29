package org.artempopov.client.net

import org.apache.logging.log4j.LogManager
import org.artempopov.ServerFirst.proto.RequestProto
import org.artempopov.ServerFirst.proto.ResponseProto
import org.artempopov.common.net.readSocketData
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.IOException
import java.net.Socket

const val PACKET_VERSION = 0x101
// request timeout in ms
const val REQUEST_TIMEOUT = 100

/**
 * All network specific functions for client
 */
object NetworkManager {

    private val LOG = LogManager.getLogger()

    private var socketToServer: Socket? = null

    /**
     * Connect to server
     */
    fun connectToServer(host: String, port: Int) {
        try {
            socketToServer = Socket(host, port)
        } catch (e: Exception) {
            throw CannotConnectToServerException(e)
        }
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
        val outStream = BufferedOutputStream(socketToServer!!.getOutputStream())

        outStream.write(byteArray)
    }

    private fun waitForResponse(): ResponseProto.Response {
        val rawResponse = getRawResponse()
        return ResponseProto.Response.parseFrom(rawResponse)
    }

    private fun getRawResponse(): ByteArray {
        return readSocketData(socketToServer!!)
    }

    private fun checkConnection() {
        if (socketToServer == null) {
            throw IllegalStateException("Network manager must be connected to server" +
                    " in order to send request. Use connect() method.")
        }
    }
}