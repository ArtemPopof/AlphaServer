package org.artempopov.serverFirst

import org.apache.logging.log4j.LogManager
import org.artempopov.serverFirst.net.Dispatcher
import org.artempopov.serverFirst.proto.RequestProto
import org.junit.Test
import java.net.Socket

const val TEST_PORT = 27028

/**
 * Various handler tests
 *
 * @author Artem Popov
 */
class DispatcherTest {
    val LOG = LogManager.getLogger()

    @Test
    fun testInvalidMessageDelivery() {
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

    private fun createAndStartServer(): Dispatcher {
        return Dispatcher(TEST_PORT)
    }
}