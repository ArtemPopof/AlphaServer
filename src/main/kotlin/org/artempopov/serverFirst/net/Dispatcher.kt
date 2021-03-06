package org.artempopov.serverFirst.net

import org.apache.logging.log4j.LogManager
import org.artempopov.client.net.CLIENT_LISTEN_PORT
import org.artempopov.serverFirst.handler.*
import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.serverFirst.util.createErrorResponse
import org.artempopov.common.net.readSocketData
import org.artempopov.serverFirst.dto.Client
import org.artempopov.serverFirst.storage.ClientManager
import java.io.BufferedOutputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


private const val MAX_THREADS = 100
private const val RESPONSE_THREADS = 30

private const val SERVER_PORT = 27029

/**
 * Dispatches all request to corresponding handlers.
 *
 * First node where all packet come
 */
object Dispatcher {

    private val LOG = LogManager.getLogger()

    /**
     * Socket that listens to all incoming packets
     */
    private val dispatherSocket: ServerSocket = ServerSocket(SERVER_PORT)

    /**
     * Stop condition for receiving loop
     */
    private var running: Boolean = true

    /**
     * Thread where accept from sockets happens
     */
    private var listeningThread: Thread = Thread(createListenTask())

    /**
     * Threads from this executor handles requests
     */
    private var handlers: ExecutorService = Executors.newFixedThreadPool(MAX_THREADS)

    /**
     * Response threads
     */
    private val responseThreads = Executors.newFixedThreadPool(RESPONSE_THREADS)


    /**
     * Init handler but to start actual listening start() func must be called
     */
    init {
        listeningThread.start()
    }

    private fun createListenTask(): Runnable {
        return Runnable {
            while (!Thread.currentThread().isInterrupted) {
                val socket = dispatherSocket.accept()

                createWorkerForSocket(socket)
            }
        }
    }

    private fun createWorkerForSocket(socket: Socket) {
        handlers.execute(SocketHandler(socket))
    }

    class SocketHandler(private val socket: Socket): Runnable {
        private val LOG = LogManager.getLogger()

        override fun run() {
            try {
                val bytes = readSocketData(socket)

                //for some reason some request is not a requests but empty arrays
                if (bytes.isEmpty()) {
                    return
                }

                LOG.debug("Message bytes: " + Arrays.toString(bytes))

                val protoMessage = parseProtoMessage(bytes)

                LOG.debug("Request accepted: " + protoMessage)

                var protoResponse: ByteArray?
                try {
                    protoResponse = handleProtoMessage(protoMessage)
                } catch (e: InvalidRequestException) {
                    LOG.error("Invalid request from client: " + protoMessage.clientId)
                    LOG.error(e.message)
                    protoResponse = createErrorResponse(ResponseProto.ErrorType.BAD_REQUEST, e.message).toByteArray()
                }

                if (protoResponse != null) {
                    sendResponse(protoResponse)
                }

            } catch (e: Exception) {
                LOG.error("ERROR OCCURRED: " + e)
                LOG.debug(e.printStackTrace())
            }
        }

        private fun parseProtoMessage(socketData: ByteArray): RequestProto.Request {
            return RequestProto.Request.parseFrom(socketData)
        }

        private fun handleProtoMessage(protoMessage: RequestProto.Request) : ByteArray {
            var response = ByteArray(5)

            when (protoMessage.type) {
                RequestProto.RequestType.MOVE ->
                    response = MoveHandler.handleMove(protoMessage)

                RequestProto.RequestType.CHANGE_COLOR ->
                    response = ShapeHandler.handleShapeChange(protoMessage)

               RequestProto.RequestType.CHANGE_SHAPE ->
                    response = ShapeHandler.handleShapeChange(protoMessage)

                RequestProto.RequestType.REGISTRATION ->
                    response = RegistrationHandler.handleRegistration(protoMessage, getHost())

//                RequestProto.RequestType.UNREGISTRATION ->
//                    response = RegistrationHandler.handleUnregistration(protoMessage)

                null -> throw IllegalArgumentException("Type is null")
            }

            return response
        }

        private fun getHost(): String {
            return socket.inetAddress.hostAddress
        }

        private fun sendResponse(response: ByteArray) {
            val outputStream = socket.getOutputStream()
            if (outputStream == null) {
                throw IllegalStateException("Incoming client output stream is null")
            }

            val bos = BufferedOutputStream(socket.getOutputStream())
            bos.write(response)

            bos.close()
        }
    }

    /**
     * This function stops any opened by this handler thread
     */
    fun stop() {
        running = false

        handlers.shutdownNow()

        dispatherSocket.close()
    }

    /**
     * Send message to specific clients
     */
    fun sendMessageToAllClients(bytes: ByteArray, clients: Collection<Client>) {
        for (client in clients) {
            sendMessageToClient(bytes, client)
        }
    }

    /**
     * Send message to specific client
     */
    fun sendMessageToClient(bytes: ByteArray, client: Client) {
        responseThreads.execute(sendingTask(bytes, client))
    }

    private fun sendingTask(bytes: ByteArray, client: Client): Runnable {
        return Runnable {
            try {
                val socket = Socket(client.host, CLIENT_LISTEN_PORT)
                org.artempopov.common.net.send(bytes, socket)
            } catch (e: Exception) {
                LOG.error("Cannot send packet for client: ${client.host}, id = ${client.id}")
                LOG.error("Error: $e")
                ClientManager.unregisterClient(client)
            }
        }
    }
}