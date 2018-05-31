package org.artempopov.serverFirst.dto

import java.awt.Color
import java.awt.Point

/**
 * Shape dto
 */
data class Shape(val shapeType: ShapeType, val color: ShapeColor, val position: Point)

enum class ShapeType(val description: String) {
    CIRCLE("Circle"),
    SQUARE("Square"),
    TRIANGLE("Triangle");
}

enum class ShapeColor(val color: Color) {
    RED(Color.red), GREEN(Color.green), BLUE(Color.blue), BLACK(Color.BLACK), ORANGE(Color.ORANGE),
    VIOLET(Color(200,20, 200))
}