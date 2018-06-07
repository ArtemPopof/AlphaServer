package org.artempopov.client

import org.artempopov.client.controller.BotController
import org.artempopov.client.controller.DefaultController

/**
 * Entry for bot client main
 */
fun botMain(args: Array<String>) {
    BotController.init()
}