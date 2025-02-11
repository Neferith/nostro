package com.angelus.gamedomain.entities

enum class Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    fun rotateLeft(): Direction {
        return when (this) {
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
            EAST -> NORTH
        }
    }

    fun rotateRight(): Direction {
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
    var direction: Direction
) {
    fun changePosition(
        distance: Int,
        direction: Direction
    ): Position {
        return when (direction) {
            Direction.NORTH -> copy(y = y - distance)
            Direction.SOUTH -> copy(y = y + distance)
            Direction.WEST -> copy(x = x - distance)
            Direction.EAST -> copy(x = x + distance)
        }
    }
}
