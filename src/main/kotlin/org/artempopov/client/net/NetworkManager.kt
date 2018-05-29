package org.artempopov.client.net

import org.artempopov.ServerFirst.proto.ResponseProto
import java.net.Socket

/**
 * All network specific functions for client
 */
object NetworkManager {

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
     */
    fun sendNotifyRequest(): ResponseProto.NotifyResponse? {
        checkConnection()


        //remove
        return null
    }

    private fun checkConnection() {
        if (socketToServer == null) {
            throw IllegalStateException("Network manager must be connected to server" +
                    " in order to send request. Use connect() method.")
        }
    }
}