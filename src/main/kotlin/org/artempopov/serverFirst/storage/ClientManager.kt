package org.artempopov.serverFirst.storage

import org.artempopov.serverFirst.dto.Client
import org.artempopov.serverFirst.handler.NoSuchClientException

/**
 * Manage clients and all associated parameters
 *
 * (in future releases it may evolve as some DB bridge)
 */
object ClientManager {

    private val clients = ArrayList<Client>()

    /**
     * Register new client
     *
     * @param client info of desired client
     * @return clientId of new client
     */
    fun registerClient(client: Client): Long {
        clients.add(client)

        return client.id
    }

    /**
     * Get client associated with this specified client id
     *
     * @throws NoSuchClientException if client with this id is not exists
     */
    fun getClient(clientID: Long): Client {
        for (client in clients) {
            if (client.id == clientID) {
                return client
            }
        }

        throw NoSuchClientException()
    }

    fun getClients(): Collection<Client> {
        return clients
    }

}