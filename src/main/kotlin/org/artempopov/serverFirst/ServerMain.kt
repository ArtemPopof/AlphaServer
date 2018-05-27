package org.artempopov.serverFirst

import org.artempopov.serverFirst.net.RequestHandler

fun main(args: Array<String>) {
    println("Starting server...")

    val server = RequestHandler(27015)

    println("Server listening...")
}