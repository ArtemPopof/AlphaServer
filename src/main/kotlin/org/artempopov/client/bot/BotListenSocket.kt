package org.artempopov.client.bot

import org.artempopov.client.net.CLIENT_LISTEN_PORT
import org.artempopov.client.net.UpdateListener
import org.artempopov.serverFirst.proto.ResponseProto
import java.net.ServerSocket

/**
 * Socket where all response packets and update packets end up
 */
object BotListenSocket {
    private val socket = ServerSocket(CLIENT_LISTEN_PORT)

    /**
     * Start recieve loop
     */
    fun listen() {
        val listenThread = Thread(createListenTask(), "BotListener")
        listenThread.start()
    }

    private fun createListenTask(): Runnable {
        return Runnable {
            while (!Thread.currentThread().isInterrupted) {
                val clientSocket = socket.accept()
                clientSocket.close()
            }
        }
    }
}
