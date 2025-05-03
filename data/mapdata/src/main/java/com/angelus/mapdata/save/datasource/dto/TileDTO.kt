package com.angelus.mapdata.save.datasource.dto

import com.angelus.gamedata.data.dto.EntityPositionDTO
import com.angelus.gamedata.data.dto.InventoryDTO
import kotlinx.serialization.Serializable

@Serializable
data class TileDTO (
    val type: String,
    val walkable: Boolean, // Par défaut, on prend la valeur du TileType,
    val transition: EntityPositionDTO?,
    val inventory: InventoryDTO?
)