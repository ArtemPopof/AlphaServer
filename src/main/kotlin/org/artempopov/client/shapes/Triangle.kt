package org.artempopov.client.shapes

import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import java.awt.Graphics
import java.awt.Point
import java.awt.Polygon

const val TRIANGLE_SIZE = 20

/**
 * Drawable triangle class
 */
class Triangle(color: ShapeColor, position: Point, override val id: Long): Shape(color, ShapeType.TRIANGLE, position, id) {
    override fun draw(g: Graphics) {
        val oldColor = g.color

        g.color = color.color

        g.fillPolygon(createTrianglePolygon())

        g.color = oldColor
    }

    private fun createTrianglePolygon(): Polygon {
        val xArray = IntArray(3)
        val yArray = IntArray(3)

        xArray[0] = position.x
        yArray[0] = position.y

        xArray[1] = position.x + TRIANGLE_SIZE
        yArray[1] = position.y

        xArray[2] = position.x + TRIANGLE_SIZE / 2
        yArray[2] = position.y + TRIANGLE_SIZE

        return Polygon(xArray, yArray, 3)
    }

}