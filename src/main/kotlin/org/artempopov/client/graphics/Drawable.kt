package org.artempopov.client.graphics

import java.awt.Graphics

/**
 * Drawable element of scene
 */
interface Drawable {
    /**
     * Drawable name associated with this object
     */
    val id: String

    /**
     * Draw element
     */
    fun draw(g: Graphics)
}
