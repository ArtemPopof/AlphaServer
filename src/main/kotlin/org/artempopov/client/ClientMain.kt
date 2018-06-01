package org.artempopov.client

import org.artempopov.client.gui.RegistrationForm
import org.artempopov.client.net.NetworkManager

fun main(args : Array<String>) {
    NetworkManager.connectToServer()

    RegistrationForm()
}