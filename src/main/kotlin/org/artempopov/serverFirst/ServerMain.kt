package org.artempopov.serverFirst

import org.artempopov.serverFirst.net.RequestHandler

fun main(args: Array<String>) {
    println("Starting server...")

    RequestHandler(27029)

    println("Server listening...")
}