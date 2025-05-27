package com.angelus.dungeonengine.model

import com.angelus.gamedomain.entities.item.Inventory
import com.angelus.mapdomain.entities.TilePosition
import com.angelus.mapdomain.entities.TileType

data class TileUiState(
    val type: TileType,
    val inventory: Inventory? = null
)

fun TilePosition.toUiState(): TileUiState {
    return TileUiState(
        type = this.type,
        inventory = this.inventory?.copy()
    )
}