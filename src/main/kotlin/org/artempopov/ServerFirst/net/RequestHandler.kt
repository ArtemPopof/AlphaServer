package org.artempopov.ServerFirst.net

import org.apache.logging.log4j.LogManager
import org.artempopov.ServerFirst.proto.RequestProto
import org.artempopov.ServerFirst.handler.InvalidRequestException
import org.artempopov.ServerFirst.handler.MoveHandler
import org.artempopov.ServerFirst.handler.RegistrationHandler
import org.artempopov.ServerFirst.handler.ShapeHandler
import org.artempopov.ServerFirst.proto.ResponseProto
import org.artempopov.ServerFirst.util.createErrorResponse
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
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

                var protoResponse: ByteArray? = null
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
            var response = ByteArray(5)

            when (protoMessage.type) {
                RequestProto.RequestType.MOVE ->
                    response = MoveHandler.handleMove(protoMessage)

                RequestProto.RequestType.CHANGE_COLOR ->
                    response = ShapeHandler.handleShapeChange(protoMessage)
//
//                RequestProto.RequestType.CHANGE_SHAPE ->
//                    response = ShapeHandler.handleShapeChange(protoMessage)

                RequestProto.RequestType.REGISTRATION ->
                    response = RegistrationHandler.handleRegistration(protoMessage)

//                RequestProto.RequestType.UNREGISTRATION ->
//                    response = RegistrationHandler.handleUnregistration(protoMessage)

                null -> throw IllegalArgumentException("Type is null")
            }

            return response
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

        serverSocket.close()
    }
}