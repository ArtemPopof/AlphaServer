package org.artempopov.client.net

import org.artempopov.serverFirst.proto.ResponseProto

/**
 * Object that want to receive notify packets from server
 */
interface UpdateListener {

    /**
     * Update packet arrived
     */
    fun onUpdate(notifyResponse: ResponseProto.NotifyResponse)
}
