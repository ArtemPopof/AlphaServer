package org.artempopov.serverFirst.dto

import java.awt.Point

/**
 * Object represents client
 */
class Client(var shape: ShapeType, var color: ShapeColor, var position: Point = Point(0, 0), val host: String)