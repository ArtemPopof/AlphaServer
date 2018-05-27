package org.artempopov.serverFirst

import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail
import org.artempopov.serverFirst.handler.InvalidRequestException
import org.artempopov.serverFirst.handler.MoveHandler
import org.artempopov.serverFirst.proto.RequestProto
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.serverFirst.util.createEmptyResponse
import org.junit.Test
import java.awt.Point

/**
 * Move handler tests
 */
class MoveHandlerTest {

    @Test
    fun testBadMoveRequest() {
        MoveHandler.clientsPosition[1] = Point(0, 0)
        try {
            MoveHandler.handleMove(createBadRequest())
        } catch (e: InvalidRequestException) {
            return
        }

        fail()
    }

    @Test fun testGoodRequest() {
        MoveHandler.clientsPosition[1] = Point(0,0)
        val response = MoveHandler.handleMove(createGoodRequest())
        val expectedResponse = createEmptyResponse()
        assertEquals(expectedResponse, response)
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