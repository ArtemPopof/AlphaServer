package org.artempopov.client.shapes

import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import java.awt.Graphics
import java.awt.Point

const val CIRCLE_SIZE = 20

/**
 * Drawable circle class
 */
class Circle(color: ShapeColor, position: Point, override val id: Long): Shape(color, ShapeType.CIRCLE, position, id) {

    override fun draw(g: Graphics) {
        val oldColor = g.color

        g.color = color.color

        g.fillOval(position.x, position.y, CIRCLE_SIZE, CIRCLE_SIZE)

        g.color = oldColor
    }

}