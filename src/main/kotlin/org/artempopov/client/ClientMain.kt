package org.artempopov.client

import org.artempopov.client.gui.MainFrame
import org.artempopov.client.net.NetworkManager
import org.artempopov.client.shapes.Square
import org.artempopov.client.world.WorldUpdater

fun main(args : Array<String>) {
    NetworkManager.connectToServer("localhost", 27029)
    val scene = MainFrame()


    // init scene manipulations
    scene.addToScene(Square(50, 50))
    scene.addToScene(Square(200, 77))

    scene.repaint()

    val worldUpdater = WorldUpdater
    worldUpdater.setScene(scene)

    worldUpdater.start()
}