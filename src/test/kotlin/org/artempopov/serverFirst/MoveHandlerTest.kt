package org.artempopov.serverFirst

import org.artempopov.serverFirst.handler.InvalidRequestException
import org.artempopov.serverFirst.handler.MoveHandler
import org.artempopov.serverFirst.proto.RequestProto
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

/**
 * Move handler tests
 */
class MoveHandlerTest {

    @Test
    fun testBadMoveRequest() {
        MoveHandler.registerClient(2)
        try {
            MoveHandler.handleMove(createBadRequest())
        } catch (e: InvalidRequestException) {
            return
        }

        fail()
    }

    @Test fun testGoodRequest() {
        MoveHandler.registerClient(1)
        val response = MoveHandler.handleMove(createGoodRequest())
        assertEquals(0, response.size)
    }

    private fun createGoodRequest(): RequestProto.Request {
        val request = RequestProto.Request.newBuilder()

        request.type = RequestProto.RequestType.MOVE
        request.clientId = 1
        request.moveRequest = createMoveRequest()
        request.requestPacketVersion = 1

        return request.build()
    }

    private fun createMoveRequest(): RequestProto.MoveRequest {
        val request = RequestProto.MoveRequest.newBuilder()

        request.direction = RequestProto.MoveDirection.RIGHT

        return request.build()
    }

    private fun createBadRequest(): RequestProto.Request {
        val request = RequestProto.Request.newBuilder()

        request.type = RequestProto.RequestType.MOVE
        request.clientId = 1
        request.requestPacketVersion = 1

        return request.build()
    }
}