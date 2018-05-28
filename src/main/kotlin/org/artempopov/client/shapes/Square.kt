package org.artempopov.client.shapes

import org.artempopov.client.graphics.Drawable
import java.awt.Color
import java.awt.Graphics
import java.awt.Point

var lastSquareId = 0

const val SQUARE_SIZE = 20

/**
 * Square shape class
 */
class Square(x: Int, y: Int) : Drawable {
    override val id = "Square" + lastSquareId++

    val position = Point(x, y)

    override fun draw(g: Graphics) {
        val oldColor = g.color

        g.color = Color.BLUE

        g.fillRect(position.x, position.y, SQUARE_SIZE, SQUARE_SIZE)

        g.color = oldColor
    }

}