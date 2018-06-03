package org.artempopov.client.controller

import org.apache.logging.log4j.LogManager
import org.artempopov.client.gui.BotSettingsWindow
import org.artempopov.client.gui.ChoseAddressFrame
import org.artempopov.client.net.Connection
import org.artempopov.client.registration.RegistrationManager
import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import javax.swing.JFrame

object BotController: Controller {
    private val LOG = LogManager.getLogger()

    private var host: String? = null
    private var port: Int? = null

    private var botsId: List<Long>? = null

    override fun registrationCompleted() {
        LOG.info("Bot registration complete")
        LOG.info("Registered ${botsId!!.size} bots")
    }

    override fun addressIsSet(host: String, port: Int, owner: JFrame) {
        this.host = host
        this.port = port

        owner.dispose()

        BotSettingsWindow()
    }

    override fun init() {
        ControllerFactory.currentController = BotController

        ChoseAddressFrame()
    }

    /**
     * When settings for bot is set
     */
    fun botConfigurationComplete(botCount: Int) {
        val botConnections = openConnections(botCount)
        registerBots(botConnections)

        registrationCompleted()
    }

    private fun openConnections(botCount: Int): List<Connection> {
        val connections = ArrayList<Connection>(botCount)

        for (i in 0..botCount) {
            connections.add(Connection(host as String, port as Int))
        }

        return connections
    }

    private fun registerBots(bots: List<Connection>) {
        for (bot in bots) {
            RegistrationManager.register(ShapeType.SQUARE, ShapeColor.BLUE, bot)
        }
    }
}