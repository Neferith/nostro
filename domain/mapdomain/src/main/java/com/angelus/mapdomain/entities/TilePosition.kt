package com.angelus.mapdomain.entities

import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.item.Inventory

data class TilePosition(val tile: Tile, val position: Position) {

    val type: TileType  get() = tile.type
    val inventory: Inventory? get() = tile.inventory

}
