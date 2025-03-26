package com.angelus.mapdomain.entities

import com.angelus.gamedomain.entities.EntityPosition

enum class TileType(
    val defaultWalkable: Boolean,
    val isWall: Boolean
) {
    STONE_FLOOR(true, false),
    STONE_WALL(false, true),
    STONE_DOOR(false, true)
}

data class Tile(val type: TileType,
                val walkable: Boolean = type.defaultWalkable, // Par défaut, on prend la valeur du TileType,
                val transition: EntityPosition? = null
 ) {

}