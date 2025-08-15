package com.angelus.dungeonengine.model

import com.angelus.npc.domain.entities.TurnType
import com.angelus.gamedomain.entities.item.Inventory
import com.angelus.mapdomain.entities.TilePosition
import com.angelus.mapdomain.entities.TileType

data class TileUiState(
    val type: TileType,
    val inventory: Inventory? = null,
    val npc: List<TurnType.NPC> = emptyList()
)

fun TilePosition.toUiState(npc: List<TurnType.NPC> ): TileUiState {
    return TileUiState(
        type = this.type,
        inventory = this.inventory?.copy(),
        npc = npc
    )
}