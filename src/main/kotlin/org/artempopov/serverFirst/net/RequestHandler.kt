package org.artempopov.serverFirst.net

import org.apache.logging.log4j.LogManager
import org.artempopov.serverFirst.handler.InvalidRequestException
import org.artempopov.serverFirst.handler.MoveHandler
import org.artempopov.serverFirst.proto.RequestProto
import java.io.BufferedInputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

const val MAX_THREADS = 20
const val BUFFER_SIZE = 1024

/**
 * RequestHandler handles request received from clients
 *
 * it uses TCP for transport and protobuf for serialization
 */
class RequestHandler(port: Int) {


    /**
     * Socket that listens to all incoming packets
     */
    private val serverSocket: ServerSocket = ServerSocket(port)

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
     * Init handler but to start actual listening start() func must be called
     */
    init {
        listeningThread.start()
    }

    private fun createListenTask(): Runnable {
        return Runnable {
            while (!Thread.currentThread().isInterrupted) {
                val socket = serverSocket.accept()

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
                val bytes = readSocketData()

                LOG.debug("Message bytes: " + bytes)

                val protoMessage = parseProtoMessage(bytes)

                LOG.debug("Request accepted: " + protoMessage)

                val protoResponse = try {
                    handleProtoMessage(protoMessage)
                } catch (e: InvalidRequestException) {
                    LOG.error("Invalid request from client: " + protoMessage.clientId)
                    LOG.error(e.message)
                }

                //sendResponse(protoResponse)
            } catch (e: Exception) {
                LOG.error("ERROR OCCURRED: " + e)
                LOG.debug(e.printStackTrace())
            }
        }

        private fun readSocketData(): ByteArray {
            val bis = BufferedInputStream(socket.getInputStream())

            val buffer = ByteArray(BUFFER_SIZE)

            var byte = bis.read()
            var counter = 0
            while (byte != -1) {
                buffer[counter++] = byte.toByte()
                byte = try {
                    bis.read()
                } catch (e: Exception) {
                    LOG.error("Error while reading data from socket: " + e)
                    throw e
                }
            }

            val data = ByteArray(counter)

            System.arraycopy(buffer, 0, data, 0, counter)

            return data
        }

        private fun parseProtoMessage(socketData: ByteArray): RequestProto.Request {
            return RequestProto.Request.parseFrom(socketData)
        }

        private fun handleProtoMessage(protoMessage: RequestProto.Request) : ByteArray {
            var response: ByteArray = ByteArray(5)

            when (protoMessage.type) {
                RequestProto.RequestType.MOVE ->
                    response = MoveHandler.handleMove(protoMessage)
//
//                RequestProto.RequestType.CHANGE_COLOR ->
//                    response = ShapeHanler.handleColorChange(protoMessage)
//
//                RequestProto.RequestType.CHANGE_SHAPE ->
//                    response = ShapeHandler.handleShapeChange(protoMessage)
//
//                RequestProto.RequestType.REGISTRATION ->
//                    response = RegistrationHandler.handleRegistration(protoMessage)
//
//                RequestProto.RequestType.UNREGISTRATION ->
//                    response = RegistrationHandler.handleUnregistration(protoMessage)

                null -> throw IllegalArgumentException("Type is null")
            }

            return response
        }
    }

    /**
     * This function
     */
    fun stop() {
        running = false

        serverSocket.close()
    }
}