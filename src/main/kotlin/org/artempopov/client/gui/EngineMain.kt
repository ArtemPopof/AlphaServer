package org.artempopov.client.gui

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
    var worldUpdater: WorldUpdater? = null

    init {
        this.isResizable = false
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
        this.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        this.isVisible = true

        this.add(surface)

        this.addKeyListener(PlayerKeyListener())
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
    fun updateScene() {
        surface.repaint()
    }

    class PlayerKeyListener(): KeyListener {
        override fun keyTyped(e: KeyEvent?) {
        }

        override fun keyPressed(e: KeyEvent?) {
            if (e == null) {
                return
            }

            when (e.keyCode) {
                KeyEvent.VK_W -> worldUpdater!!.setMoveDirection(RequestProto.MoveDirection.UP)
                KeyEvent.VK_S -> worldUpdater!!.setMoveDirection(RequestProto.MoveDirection.DOWN)
                KeyEvent.VK_A -> worldUpdater!!.setMoveDirection(RequestProto.MoveDirection.LEFT)
                KeyEvent.VK_D -> worldUpdater!!.setMoveDirection(RequestProto.MoveDirection.RIGHT)
            }
        }

        override fun keyReleased(e: KeyEvent?) {
            if (e == null) {
                return
            }

            when (e.keyCode) {
                KeyEvent.VK_W -> worldUpdater!!.setMoveDirection(null)
                KeyEvent.VK_S -> worldUpdater!!.setMoveDirection(null)
                KeyEvent.VK_A -> worldUpdater!!.setMoveDirection(null)
                KeyEvent.VK_D -> worldUpdater!!.setMoveDirection(null)
            }
        }
    }

}