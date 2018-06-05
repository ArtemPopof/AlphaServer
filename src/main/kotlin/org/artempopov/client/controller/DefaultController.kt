package org.artempopov.client.controller

import org.artempopov.client.gui.ChoseAddressFrame
import org.artempopov.client.gui.EngineMain
import org.artempopov.client.gui.RegistrationForm
import org.artempopov.client.net.CannotConnectToServerException
import org.artempopov.client.net.Channel
import org.artempopov.client.world.WorldUpdater
import org.artempopov.serverFirst.proto.RequestProto
import javax.swing.JFrame
import javax.swing.JOptionPane

/**
 * Switches windows when it needed
 */
object DefaultController: Controller {

    private var channel: Channel? = null

    override fun registrationCompleted() {
        EngineMain

        val worldUpdater = WorldUpdater()

        // now all messages will be dispatched from channel to worldUpdater
        channel!!.addUpdateListener(worldUpdater)
    }

    override fun addressIsSet(host: String, port: Int, owner: JFrame) {
        try {
            owner.dispose()

            channel = Channel(host, port)

            RegistrationForm(channel as Channel)
        } catch (e: CannotConnectToServerException) {
            JOptionPane.showMessageDialog(owner, "Registration error: ${e.message}",
                    "Registration", JOptionPane.ERROR_MESSAGE)

        }
    }

    override fun init() {
        ChoseAddressFrame()
    }

    /**
     * Send move request to server
     */
    fun movePlayer(moveDirection: RequestProto.MoveDirection?) {
        if (moveDirection != null) {
            channel!!.sendMoveRequest(moveDirection)
        }
    }

}
