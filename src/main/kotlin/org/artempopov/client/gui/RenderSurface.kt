package org.artempopov.client.gui

import org.artempopov.client.graphics.Drawable
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

/**
 * Main render surface where all paint activity resides
 */
class RenderSurface: JPanel() {

    /**
     * Drawable elements of scene
     */
    private val drawables = ArrayList<Drawable>()

    override fun paint(g: Graphics) {
        g.color = Color.WHITE
        g.fillRect(0, 0, width, height)

        drawables.forEach{
            it.draw(g)
        }

        g.dispose()
    }

    /**
     * Add new drawable element to scene
     *
     * Element will be painted as new frame come
     */
    fun addToScene(drawable: Drawable) {
        drawables.add(drawable)
    }

    /**
     * Remove element from scene with specified name
     */
    fun removeDrawable(name: String) {
        for (drawable in drawables) {
            if (drawable.id.equals(name)) {
                drawables.remove(drawable)
            }
        }
    }

    /**
     * Remove all drawables from scene
     */
    fun clear() {
        drawables.clear()
    }
}
