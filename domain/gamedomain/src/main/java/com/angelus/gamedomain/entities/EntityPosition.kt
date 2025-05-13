package com.angelus.gamedomain.entities

enum class Direction {
    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT
}

enum class Orientation {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    fun rotateLeft(): Orientation {
        return when (this) {
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
            EAST -> NORTH
        }
    }

    fun rotateRight(): Orientation {
        return when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }
}

enum class Rotation {
    LEFT,
    RIGHT
}

data class Position(
    val x: Int,
    val y: Int
) {
    override fun toString(): String = "$x|$y"
}

fun String.toPosition(): Position {
    val parts = this.split("|")
    require(parts.size == 2) { "Invalid position format. Expected format: x|y" }
    val x = parts[0].toIntOrNull() ?: error("Invalid x coordinate: ${parts[0]}")
    val y = parts[1].toIntOrNull() ?: error("Invalid y coordinate: ${parts[1]}")
    return Position(x, y)
}

data class EntityPosition(
    val mapId: String,
    val position: Position,
    val orientation: Orientation
) {
    fun moveForward(distance: Int) = move(distance, 0)
    fun moveBackward(distance: Int) = move(-distance, 0)
    fun strafeLeft(distance: Int) = move(0, -distance)
    fun strafeRight(distance: Int) = move(0, distance)

    val x: Int get() = position.x
    val y: Int get() = position.y


    fun changePosition(distance: Int, direction: Direction): EntityPosition {
        return when (direction) {
            Direction.FORWARD -> moveForward(distance)
            Direction.BACKWARD -> moveBackward(distance)
            Direction.LEFT -> strafeLeft(distance)
            Direction.RIGHT -> strafeRight(distance)
        }
    }

    private fun move(forwardOffset: Int, sideOffset: Int): EntityPosition {
        val (xOffset, yOffset) = when (orientation) {
            Orientation.NORTH -> 0 to -1
            Orientation.SOUTH -> 0 to 1
            Orientation.WEST -> -1 to 0
            Orientation.EAST -> 1 to 0
        }

        return copy(position = position.copy(
            x = position.x + xOffset * forwardOffset + sideOffset,
            y = position.y + yOffset * forwardOffset + sideOffset
        ))
    }
}
