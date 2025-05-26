package com.angelus.dungeonengine.model

import com.angelus.gamedomain.entities.item.Inventory
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.entities.TileType

data class TileUiState(
    val type: TileType,
    //val walkable: Boolean = type.defaultWalkable, // Par défaut, on prend la valeur du TileType,
   // val transition: EntityPosition? = null,
    val inventory: Inventory? = null
)

fun Tile.toUiState(): TileUiState {
    return TileUiState(
        type = this.type,
        inventory = this.inventory?.copy()
    )
}