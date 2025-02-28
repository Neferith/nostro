package com.angelus.mapdomain.entities

enum class TileType(val defaultWalkable: Boolean, val isWall: Boolean) {
    STONE_FLOOR(true, false),
    STONE_WALL(false, true)
}

data class Tile(val type: TileType,
                val walkable: Boolean = type.defaultWalkable // Par défaut, on prend la valeur du TileType
 ) {

}