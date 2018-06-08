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

    /**
     * When some client unregistered from sever and must be erased from
     * drawing surface
     */
    fun onUnregisterEvent(unregisterEvent: ResponseProto.UnregisterEvent)
}
