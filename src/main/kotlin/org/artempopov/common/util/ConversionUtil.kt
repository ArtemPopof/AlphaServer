package org.artempopov.common.util

import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import org.artempopov.serverFirst.proto.Common
import java.awt.Point

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

/**
 * Convert proto to bean Color
 *
 * @param proto based object
 * @return ShapeColor object
 */
fun toDto(color: Common.Color): ShapeColor {
    return when (color) {
        Common.Color.BLACK -> ShapeColor.BLACK
        Common.Color.BLUE -> ShapeColor.BLUE
        Common.Color.GREEN -> ShapeColor.GREEN
        Common.Color.RED -> ShapeColor.RED
        Common.Color.ORANGE -> ShapeColor.ORANGE
        Common.Color.VIOLET -> ShapeColor.VIOLET
    }
}

/**
 * From proto position to point
 */
fun fromProto(position: Common.Position): Point {
    return Point(position.x, position.y)
}