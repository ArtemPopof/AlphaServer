package org.artempopov.client

import org.artempopov.client.controller.DefaultController

/**
 * Client entry Point
 */
fun clientMain(args : Array<String>) {
    DefaultController.init()
}