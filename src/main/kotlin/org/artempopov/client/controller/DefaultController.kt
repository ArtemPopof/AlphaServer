package org.artempopov.client.controller

import org.artempopov.client.gui.ChoseAddressFrame
import org.artempopov.client.gui.EngineMain
import org.artempopov.client.gui.RegistrationForm
import org.artempopov.client.net.CannotConnectToServerException
import org.artempopov.client.net.Connection
import org.artempopov.client.world.WorldUpdater
import javax.swing.JFrame
import javax.swing.JOptionPane

/**
 * Switches windows when it needed
 */
object DefaultController: Controller {

    private var connection: Connection? = null

    override fun registrationCompleted() {
        val worldUpdater = WorldUpdater(connection as Connection)
        EngineMain.worldUpdater = worldUpdater
        worldUpdater.start()
    }

    override fun addressIsSet(host: String, port: Int, owner: JFrame) {
        try {
            owner.dispose()

            connection = Connection(host, port)

            RegistrationForm(connection as Connection)
        } catch (e: CannotConnectToServerException) {
            JOptionPane.showMessageDialog(owner, "Registration error: ${e.message}",
                    "Registration", JOptionPane.ERROR_MESSAGE)

        }
    }

    override fun init() {
        ChoseAddressFrame()
    }

}
