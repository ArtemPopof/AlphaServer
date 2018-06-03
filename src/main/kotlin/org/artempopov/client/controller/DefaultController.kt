package org.artempopov.client.controller

import org.artempopov.client.gui.ChoseAddressFrame
import org.artempopov.client.gui.MainFrame
import org.artempopov.client.gui.RegistrationForm
import org.artempopov.client.net.CannotConnectToServerException
import org.artempopov.client.net.NetworkManager
import org.artempopov.client.world.WorldUpdater
import javax.swing.JFrame
import javax.swing.JOptionPane

/**
 * Switches windows when it needed
 */
object DefaultController: Controller {

    override fun registrationCompleted() {
        val scene = MainFrame()
        val worldUpdater = WorldUpdater
        worldUpdater.setScene(scene)
        worldUpdater.start()
    }

    override fun addressIsSet(host: String, port: Int, owner: JFrame) {
        try {
            owner.dispose()

            NetworkManager.init(host, port)
            NetworkManager.connectToServer()

            RegistrationForm()
        } catch (e: CannotConnectToServerException) {
            JOptionPane.showMessageDialog(owner, "Registration error: ${e.message}",
                    "Registration", JOptionPane.ERROR_MESSAGE)

        }
    }

    override fun init() {
        ChoseAddressFrame()
    }

}
