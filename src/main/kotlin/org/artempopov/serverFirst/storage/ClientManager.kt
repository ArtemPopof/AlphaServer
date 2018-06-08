package org.artempopov.serverFirst.storage

import org.apache.logging.log4j.LogManager
import org.artempopov.serverFirst.dto.Client
import org.artempopov.serverFirst.handler.NoSuchClientException
import org.artempopov.serverFirst.handler.RegistrationHandler
import java.util.*

/**
 * Manage clients and all associated parameters
 *
 * (in future releases it may evolve as some DB bridge)
 */
object ClientManager {

    private val LOG = LogManager.getLogger()

    private val clients = Vector<Client>()

    /**
     * Register new client
     *
     * @param client info of desired client
     * @return clientId of new client
     */
    fun registerClient(client: Client): Long {
        clients.add(client)

        LOG.info("client ${client.id} registered")

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

    /**
     * Unregister specified client
     */
    fun unregisterClient(client: Client) {
        for (i in 0 until clients.size) {
            LOG.debug("i = $i")
            if (clients[i].id == client.id) {
                LOG.debug("CLIENTS SIZE: ${clients.size}")
                clients.removeAt(i)
                RegistrationHandler.clientUnregistered(client.id)
                break
            }
        }

        LOG.info("client ${client.id} unregistered")
    }

}