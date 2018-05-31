package org.artempopov.client.net

/**
 * When some server side error happens
 */
class ErrorResponseException(error: Error): Exception("ERROR ${error.message}: ${error.error}")