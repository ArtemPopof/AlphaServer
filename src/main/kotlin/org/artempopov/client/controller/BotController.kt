package org.artempopov.client.controller

import org.apache.logging.log4j.LogManager
import org.artempopov.client.gui.BotSettingsWindow
import org.artempopov.client.gui.ChoseAddressFrame
import org.artempopov.client.net.NetworkManager
import org.artempopov.client.net.PACKET_VERSION
import org.artempopov.serverFirst.proto.Common
import org.artempopov.serverFirst.proto.RequestProto
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
        NetworkManager.connectToServer()

        botsId = registerBots(botCount)

        registrationCompleted()
    }

    private fun registerBots(botCount: Int): List<Long> {
        val botsId = ArrayList<Long>(botCount)

        for (i in 0..botCount) {
            botsId.add(registerBot())
        }

        return botsId
    }

    private fun registerBot(): Long {
        val response = NetworkManager.sendRegistrationRequest(createBotRequest())
        return response.clientId
    }

    private fun createBotRequest(): RequestProto.Request {
        val request = RequestProto.Request.newBuilder()

        request.type = RequestProto.RequestType.REGISTRATION
        request.requestPacketVersion = PACKET_VERSION
        request.registrationRequest = createBotRegisterRequest()

        return request.build()
    }

    private fun createBotRegisterRequest(): RequestProto.RegistrationRequest {
        val request = RequestProto.RegistrationRequest.newBuilder()

        request.shape = Common.Shape.SQUARE
        request.color = Common.Color.BLUE

        return request.build()
    }
}