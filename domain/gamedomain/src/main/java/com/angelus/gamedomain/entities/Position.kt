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
    var x: Int,
    var y: Int,
    var orientation: Orientation
) {
    fun moveForward(distance: Int) = move(distance, 0)
    fun moveBackward(distance: Int) = move(-distance, 0)
    fun strafeLeft(distance: Int) = move(0, -distance)
    fun strafeRight(distance: Int) = move(0, distance)

    fun changePosition(distance: Int, direction: Direction): Position {
        return when (direction) {
            Direction.FORWARD -> moveForward(distance)
            Direction.BACKWARD -> moveBackward(distance)
            Direction.LEFT -> strafeLeft(distance)
            Direction.RIGHT -> strafeRight(distance)
        }
    }

    private fun move(forwardOffset: Int, sideOffset: Int): Position {
        return when (orientation) {
            Orientation.NORTH -> copy(y = y - forwardOffset, x = x - sideOffset)
            Orientation.SOUTH -> copy(y = y + forwardOffset, x = x + sideOffset)
            Orientation.WEST -> copy(x = x - forwardOffset, y = y + sideOffset)
            Orientation.EAST -> copy(x = x + forwardOffset, y = y - sideOffset)
        }
    }
}
