package org.artempopov.ServerFirst

import org.apache.logging.log4j.LogManager
import org.artempopov.ServerFirst.proto.RequestProto
import org.artempopov.ServerFirst.net.RequestHandler
import org.junit.Test
import java.net.Socket

const val TEST_PORT = 27028

/**
 * Various handler tests
 *
 * @author Artem Popov
 */
class RequestHandlerTest {
    val LOG = LogManager.getLogger()

    @Test fun testInvalidMessageDelivery() {
        val server = createAndStartServer()

        val socket = Socket("localhost", TEST_PORT)
        socket.getOutputStream().write("INVALID_MESSAGE".toByteArray())
        socket.close()

        Thread.sleep(50)

        server.stop()
    }

    @Test fun testValidMessageDelivery() {
        createAndStartServer()

        val message = createRequest()
        val socket = Socket("localhost", TEST_PORT)
        socket.getOutputStream().write(message)
        socket.close()

        Thread.sleep(50)
    }

    private fun createRequest(): ByteArray {
        val requestBuilder = RequestProto.Request.newBuilder()

        requestBuilder.type = RequestProto.RequestType.MOVE
        requestBuilder.requestPacketVersion = 107

        return requestBuilder.build().toByteArray()
    }

    private fun createAndStartServer(): RequestHandler {
        return RequestHandler(TEST_PORT)
    }
}