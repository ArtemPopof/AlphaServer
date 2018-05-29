package org.artempopov.client.net

/**
 * When some server side error happens
 */
class ErrorResponseException(code: Int, message: String): Exception("ERROR $code: $message") {

}