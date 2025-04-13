package com.angelus.mapdomain.entities

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.item.Inventory
import com.angelus.gamedomain.entities.item.isEmpty
import com.angelus.gamedomain.entities.item.isNotEmpty

enum class TileType(
    val defaultWalkable: Boolean,
    val isWall: Boolean
) {
    STONE_FLOOR(true, false),
    STONE_WALL(false, true),
    STONE_DOOR(false, true)
}

data class Tile(
    val type: TileType,
    val walkable: Boolean = type.defaultWalkable, // Par défaut, on prend la valeur du TileType,
    val transition: EntityPosition? = null,
    val inventory: Inventory? = null
) {

}

fun Tile.hasInventory(): Boolean {
    return inventory?.isNotEmpty()?:false
}