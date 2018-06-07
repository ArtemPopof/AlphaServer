package org.artempopov.serverFirst.dto

import java.awt.Point

private var lastClientId = 0L

/**
 * Object represents client
 */
data class Client(var shape: ShapeType, var color: ShapeColor, var position: Point = Point(0, 0), val host: String) {
    val id: Long = lastClientId++
    var connectAttempts: Int = 0
}