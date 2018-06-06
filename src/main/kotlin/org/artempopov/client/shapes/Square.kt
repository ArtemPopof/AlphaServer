package org.artempopov.client.shapes

import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import java.awt.Graphics
import java.awt.Point

const val SQUARE_SIZE = 20

/**
 * Square shape class
 */
class Square(color: ShapeColor, position: Point, id: Long): Shape(color, ShapeType.SQUARE, position, id) {
    override fun draw(g: Graphics) {
        val oldColor = g.color

        g.color = color.color

        g.fillRect(position.x, position.y, SQUARE_SIZE, SQUARE_SIZE)

        g.color = oldColor
    }

}