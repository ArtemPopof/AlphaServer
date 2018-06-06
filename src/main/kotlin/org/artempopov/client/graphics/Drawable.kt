package org.artempopov.client.graphics

import java.awt.Graphics

/**
 * Drawable element of scene
 */
interface Drawable {
    /**
     * Drawable name associated with this object
     */
    val id: Long

    /**
     * Draw element
     */
    fun draw(g: Graphics)
}
