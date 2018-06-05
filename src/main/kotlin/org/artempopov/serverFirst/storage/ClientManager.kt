package org.artempopov.serverFirst.storage

import org.artempopov.serverFirst.dto.Client
import org.artempopov.serverFirst.handler.NoSuchClientException

private var lastClientId = 0L

/**
 * Manage clients and all associated parameters
 *
 * (in future releases it may evolve as some DB bridge)
 */
object ClientManager {

    private val clients = HashMap<Long, Client>()

    /**
     * Register new client
     *
     * @param client info of desired client
     * @return clientId of new client
     */
    fun registerClient(client: Client): Long {
        val clientId = lastClientId++

        clients.put(clientId, client)

        return clientId
    }

    /**
     * Get client associated with this specified client id
     *
     * @throws NoSuchClientException if client with this id is not exists
     */
    fun getClient(clientID: Long): Client {
        if (!clients.contains(clientID)) {
            throw NoSuchClientException()
        }
        return clients[clientID] as Client
    }

}