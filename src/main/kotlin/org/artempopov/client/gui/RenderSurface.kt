package org.artempopov.client.gui

import org.artempopov.client.graphics.Drawable
import java.awt.Canvas
import java.awt.Graphics

/**
 * Main render surface where all paint activity resides
 */
class RenderSurface: Canvas() {

    /**
     * Drawable elements of scene
     */
    private val drawables = ArrayList<Drawable>()

    override fun paint(g: Graphics) {
        g.clearRect(0, 0, width, height)

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
}
