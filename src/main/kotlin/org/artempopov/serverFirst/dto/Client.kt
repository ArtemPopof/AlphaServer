package org.artempopov.serverFirst.dto

import java.awt.Point

private var lastClientId = 0L

/**
 * Object represents client
 */
data class Client(var shape: ShapeType, var color: ShapeColor, var position: Point = Point(0, 0), val host: String) {
    val id: Long = lastClientId++
    var connectAttempts: Int = 0

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other !is Client) {
            return false
        }

        return id == other.id
    }

    override fun hashCode(): Int {
        var result = shape.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + position.hashCode()
        result = 31 * result + host.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + connectAttempts
        return result
    }
}