package org.artempopov.client.gui

import org.artempopov.client.shapes.Square
import javax.swing.JFrame
import javax.swing.WindowConstants

const val WINDOW_TITLE = "Alpha Client"
const val WINDOW_HEIGHT = 500
const val WINDOW_WIDTH = 500 * 5 / 4

class MainFrame: JFrame(WINDOW_TITLE) {

    private val surface = RenderSurface()

    init {
        this.isResizable = false
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
        this.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        this.isVisible = true

        this.add(surface)

        // init scene manipulations
        surface.addToScene(Square(50, 50))
        surface.addToScene(Square(200, 77))

        surface.repaint()
    }

}