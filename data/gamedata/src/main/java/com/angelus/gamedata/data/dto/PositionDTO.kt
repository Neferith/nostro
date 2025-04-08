package com.angelus.gamedata.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PositionDTO(
    val x: Int,
    val y: Int
)

@Serializable
data class EntityPositionDTO(
    val mapId: String,
    val position: PositionDTO,
    val orientation: String
)