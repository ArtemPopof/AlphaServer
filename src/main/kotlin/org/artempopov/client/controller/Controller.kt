package org.artempopov.client.controller

import javax.swing.JFrame

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