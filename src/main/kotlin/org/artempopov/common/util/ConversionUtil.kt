package org.artempopov.common.util

import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import org.artempopov.serverFirst.proto.Common

/**
 * Utility classes for conversion
 **/

/**
 * Convert bean to proto Shape
 *
 * @param shape ShapeType object
 * @return proto based object
 */
fun fromDto(shape: ShapeType): Common.Shape {
    return when (shape) {
        ShapeType.CIRCLE -> Common.Shape.CIRCLE
        ShapeType.SQUARE -> Common.Shape.SQUARE
        ShapeType.TRIANGLE -> Common.Shape.TRIANGLE
    }
}

/**
 * Convert bean to proto Color
 *
 * @param shape ShapeColor object
 * @return proto based object
 */
fun fromDto(color: ShapeColor): Common.Color {
    return when (color) {
        ShapeColor.BLACK -> Common.Color.BLACK
        ShapeColor.BLUE -> Common.Color.BLUE
        ShapeColor.GREEN -> Common.Color.GREEN
        ShapeColor.RED -> Common.Color.RED
        ShapeColor.ORANGE -> Common.Color.ORANGE
        ShapeColor.VIOLET -> Common.Color.VIOLET
    }
}
