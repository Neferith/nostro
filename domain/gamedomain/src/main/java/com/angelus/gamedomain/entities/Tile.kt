package com.angelus.gamedomain.entities

enum class TileType(val defaultWalkable: Boolean) {
    STONE_FLOOR(true),
    STONE_WALL(false)
}

data class Tile(val type: TileType,
                val walkable: Boolean = type.defaultWalkable // Par défaut, on prend la valeur du TileType
 ) {

}