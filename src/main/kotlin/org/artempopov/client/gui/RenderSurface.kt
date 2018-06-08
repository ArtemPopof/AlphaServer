package org.artempopov.client.gui

import org.apache.logging.log4j.LogManager
import org.artempopov.client.graphics.Drawable
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

/**
 * Main render surface where all paint activity resides
 */
class RenderSurface: JPanel() {

    private val LOG = LogManager.getLogger()

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
     * Remove element with specified id from scene
     */
    fun removeDrawable(id: Long) {
        var drawable: Drawable
        for (i in 0 until drawables.size) {
            drawable = drawables[i]
            if (drawable.id == id) {
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

    /**
     * Update shape with new one
     */
    fun updateShape(shape: Drawable) {
        for (i in 0 until drawables.size) {
            if (drawables[i].id == shape.id) {
                drawables[i] = shape
                return
            }
        }

        drawables.add(shape)
    }
}
