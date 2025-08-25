package com.angelus.faction.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class FactionDTO(
    val id: String,
                          val name: String,
                          val description: String,
                          val relations: Map<String, Int> = emptyMap()
)
