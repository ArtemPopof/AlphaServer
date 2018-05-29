package org.artempopov.ServerFirst

import org.artempopov.ServerFirst.net.RequestHandler

fun main(args: Array<String>) {
    println("Starting server...")

    RequestHandler(27029)

    println("Server listening...")
}