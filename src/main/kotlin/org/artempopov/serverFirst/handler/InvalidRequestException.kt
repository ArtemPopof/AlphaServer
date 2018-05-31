package org.artempopov.serverFirst.handler

/**
 * Invalid format or data in request
 */
class InvalidRequestException(override val message: String) : Exception(message) {
}