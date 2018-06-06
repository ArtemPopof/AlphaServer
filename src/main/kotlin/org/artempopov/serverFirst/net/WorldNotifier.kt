package org.artempopov.serverFirst.net

import org.artempopov.client.net.CLIENT_LISTEN_PORT
import org.artempopov.common.util.fromDto
import org.artempopov.common.util.sleepRemainingTime
import org.artempopov.serverFirst.dto.Client
import org.artempopov.serverFirst.proto.Common
import org.artempopov.serverFirst.proto.ResponseProto
import org.artempopov.serverFirst.storage.ClientManager
import java.awt.Point
import java.net.Socket

private const val SERVER_UPDATE_TICK_PERIOD = 1000 / 15L

/**
 * World notifier send information about world state
 * after notify request
 *
 * @author Artem Popov
 */
object WorldNotifier {

    /**
     * Clients that moved since last tick
     */
    private var activeClients = ArrayList<Client>()
    private val updaterThread = Thread(createUpdaterTask(), "WorldNotifier")

    init {
        updaterThread.start()
    }

    private fun createUpdaterTask(): Runnable {
        return Runnable {
            var lastTime = 0L

            while (!Thread.currentThread().isInterrupted) {
                lastTime = System.currentTimeMillis()

                updateClients()

                sleepRemainingTime(lastTime, SERVER_UPDATE_TICK_PERIOD)
            }
        }
    }

    private fun updateClients() {
        val clients = ClientManager.getClients()
        val notifyPacket = createNotifyResponse()

        for (client in clients) {
            updateInfoForClient(client, notifyPacket)
        }

        activeClients.clear()
    }

    private fun updateInfoForClient(client: Client, notifyPacket: ResponseProto.Response) {
        send(notifyPacket.toByteArray(), client)
    }

    private fun createNotifyResponse(): ResponseProto.Response {
        val response = ResponseProto.Response.newBuilder()

        val shapesInfo = convertToProto(activeClients)
        response.notify = createNotify(shapesInfo)

        return response.build()
    }

    private fun convertToProto(clients: List<Client>): List<ResponseProto.ShapeInfo> {
        val protoClients = ArrayList<ResponseProto.ShapeInfo>(clients.size)

        for (client in clients) {
            protoClients.add(convertToProto(client))
        }

        return protoClients
    }

    private fun convertToProto(client: Client): ResponseProto.ShapeInfo {
        val shape = ResponseProto.ShapeInfo.newBuilder()

        shape.position = createPosition(client.position)
        shape.shape = fromDto(client.shape)
        shape.color = fromDto(client.color)
        shape.id = client.id

        return shape.build()
    }

    private fun createPosition(pos: Point): Common.Position {
        val position = Common.Position.newBuilder()

        position.x = pos.x
        position.y = pos.y

        return position.build()
    }

    private fun createNotify(shapes: List<ResponseProto.ShapeInfo>): ResponseProto.NotifyResponse {
        val notify = ResponseProto.NotifyResponse.newBuilder()

        notify.addAllShapes(shapes)

        return notify.build()
    }

    private fun send(bytes: ByteArray, client: Client) {
        val socket = Socket(client.host, CLIENT_LISTEN_PORT)
        org.artempopov.common.net.send(bytes, socket)
    }

    /**
     * Client moved since last tick
     */
    fun markClientActive(clientId: Long) {
        activeClients.add(ClientManager.getClient(clientId))
    }
}