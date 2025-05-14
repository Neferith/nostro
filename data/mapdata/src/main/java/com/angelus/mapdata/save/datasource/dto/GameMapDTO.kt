package com.angelus.mapdata.save.datasource.dto

import com.angelus.gamedata.data.dto.SizeDTO
import kotlinx.serialization.Serializable

@Serializable
data class GameMapDTO (
    val id: String,
    val mapType: String,
    val size: SizeDTO,
    val defaultTileType: String,
    val tiles: Map<String, TileDTO> = mutableMapOf()
)