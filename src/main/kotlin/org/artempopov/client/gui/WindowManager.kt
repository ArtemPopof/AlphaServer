package org.artempopov.client.gui

import org.artempopov.client.net.CannotConnectToServerException
import org.artempopov.client.net.NetworkManager
import org.artempopov.client.world.WorldUpdater
import javax.swing.JFrame
import javax.swing.JOptionPane

/**
 * Switches windows when it needed
 */
object WindowManager {

    /**
     * Registration completed
     */
    fun registrationCompleted() {
        val scene = MainFrame()
        val worldUpdater = WorldUpdater
        worldUpdater.setScene(scene)
        worldUpdater.start()
    }

    /**
     * When user set address for server
     */
    fun addressIsSet(host: String, port: Int, owner: JFrame) {
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

    /**
     * Entry point in window transition chain
     */
    fun init() {
        ChoseAddressFrame()
    }

}
