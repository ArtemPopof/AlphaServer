package org.artempopov.client.shapes

import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import java.awt.Graphics
import java.awt.Point

private var lastCircleId = 0

const val CIRCLE_SIZE = 20

/**
 * Drawable circle class
 */
class Circle(color: ShapeColor, position: Point): Shape(color, ShapeType.CIRCLE, position) {
    override val id = "Circle" + lastCircleId++

    override fun draw(g: Graphics) {
        val oldColor = g.color

        g.color = color.color

        g.fillOval(position.x, position.y, CIRCLE_SIZE, CIRCLE_SIZE)

        g.color = oldColor
    }

}