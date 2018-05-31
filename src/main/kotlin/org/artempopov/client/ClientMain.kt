package org.artempopov.client

import org.artempopov.client.gui.MainFrame
import org.artempopov.client.gui.RegistrationForm
import org.artempopov.client.net.NetworkManager
import org.artempopov.client.world.WorldUpdater

fun main(args : Array<String>) {
    NetworkManager.connectToServer()

    RegistrationForm()
}