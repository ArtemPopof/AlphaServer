package org.artempopov.client.gui

import org.artempopov.client.controller.DefaultController
import org.artempopov.client.graphics.Drawable
import org.artempopov.client.shapes.Square
import org.artempopov.client.world.WorldUpdater
import org.artempopov.serverFirst.proto.RequestProto
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.security.Key
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

private const val WINDOW_TITLE = "Alpha Client"
private const val WINDOW_HEIGHT = 500
private const val WINDOW_WIDTH = 500 * 5 / 4

object EngineMain: JFrame(WINDOW_TITLE) {

    private val surface = RenderSurface()

    init {
        this.isResizable = false
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
        this.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        this.add(surface)

        this.addKeyListener(PlayerKeyListener())

        this.isVisible = true
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
     * delete all drawables from scene
     */
    fun clear() {
        surface.clear()
    }

    /**
     * Repaint scene
     */
    fun repaintScene() {
        surface.repaint()
    }

    /**
     * Update shape on the screne with new one
     */
    fun updateShapeOnScene(shape: Drawable) {
        surface.updateShape(shape)
    }

    class PlayerKeyListener(): KeyListener {
        override fun keyTyped(e: KeyEvent?) {
        }

        override fun keyPressed(e: KeyEvent?) {
            if (e == null) {
                return
            }

            when (e.keyCode) {
                KeyEvent.VK_W -> DefaultController.movePlayer(RequestProto.MoveDirection.UP)
                KeyEvent.VK_S -> DefaultController.movePlayer(RequestProto.MoveDirection.DOWN)
                KeyEvent.VK_A -> DefaultController.movePlayer(RequestProto.MoveDirection.LEFT)
                KeyEvent.VK_D -> DefaultController.movePlayer(RequestProto.MoveDirection.RIGHT)
            }
        }

        override fun keyReleased(e: KeyEvent?) {
            if (e == null) {
                return
            }

            when (e.keyCode) {
                KeyEvent.VK_W -> DefaultController.movePlayer(null)
                KeyEvent.VK_S -> DefaultController.movePlayer(null)
                KeyEvent.VK_A -> DefaultController.movePlayer(null)
                KeyEvent.VK_D -> DefaultController.movePlayer(null)
            }
        }
    }

}