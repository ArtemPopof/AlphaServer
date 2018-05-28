package org.artempopov.ServerFirst.dto

import org.artempopov.ServerFirst.proto.RequestProto
import java.awt.Color

/**
 * Shape dto
 */
data class Shape(val id: Int, val shapeType: ShapeType, val color: ShapeColor)

enum class ShapeType(val description: String) {
    CIRCLE("Circle"),
    SQUARE("Square"),
    TRIANGLE("Triangle");
}

enum class ShapeColor(val color: Color) {
    RED(Color.red), GREEN(Color.green), BLUE(Color.blue), BLACK(Color.BLACK), ORANGE(Color.ORANGE),
    VIOLET(Color(200,20, 200))
}