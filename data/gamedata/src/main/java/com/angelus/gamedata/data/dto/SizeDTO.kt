package com.angelus.gamedata.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class SizeDTO(
    val width: Int,
    val height: Int
)