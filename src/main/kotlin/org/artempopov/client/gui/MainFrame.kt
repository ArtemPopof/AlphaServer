package org.artempopov.client.gui

import org.artempopov.client.graphics.Drawable
import org.artempopov.client.shapes.Square
import javax.swing.JFrame
import javax.swing.WindowConstants

const val WINDOW_TITLE = "Alpha Client"
private const val WINDOW_HEIGHT = 500
private const val WINDOW_WIDTH = 500 * 5 / 4

class MainFrame: JFrame(WINDOW_TITLE) {

    private val surface = RenderSurface()

    init {
        this.isResizable = false
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
        this.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        this.isVisible = true

        this.add(surface)
    }

    /**
     * Add drawable to scene
     */
    fun addToScene(drawable: Drawable) {
        surface.addToScene(drawable)
    }

    /**
     * Remove drawable from scene
     */
    fun removeFromScene(name: String) {
        surface.removeDrawable(name)
    }

    /**
     * Repaint scene
     */
    fun updateScene() {
        surface.repaint()
    }

}