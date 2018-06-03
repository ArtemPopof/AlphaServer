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
 * Controller interface
 */
interface Controller {

    /**
     * Registration completed
     */
    fun registrationCompleted()

    /**
     * When user set address for server
     */
    fun addressIsSet(host: String, port: Int, owner: JFrame)
    /**
     * Init controller
     */
    fun init()
}