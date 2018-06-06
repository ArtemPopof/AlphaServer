package org.artempopov.client.shapes

import org.artempopov.client.graphics.Drawable
import org.artempopov.serverFirst.dto.ShapeColor
import org.artempopov.serverFirst.dto.ShapeType
import java.awt.Point


/**
 * For future releases
 */
abstract class Shape(val color: ShapeColor, val type: ShapeType, var position: Point, override val id: Long): Drawable {
}
