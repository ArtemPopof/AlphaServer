package org.artempopov.client.controller

/**
 * Controller factory
 *
 * Object must call method getController() to obtain actual Factory instance
 */
object ControllerFactory {
    var currentController = DefaultController as Controller

}