package com.angelus.gamedata.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDTO(
    val id: String,
    val mainAttributes: AttributesDTO,
    val characterLevel: CharacterLevelDTO,
    val description: CharacterDescriptionDTO,
    val skills: CharacterSkillsDTO,
    val inventory: InventoryDTO
)

@Serializable
data class CharacterLevelDTO(val level: Int, val experience: Int)

@Serializable
data class CharacterDescriptionDTO(
    val name: CharacterNameDTO,
    val age: CharacterAgeDTO,
    val gender: String,
    val size: String,
    val weight: String,
    val sensitivity: String,
    val background: List<String>,
)

@Serializable
data class CharacterNameDTO(val firstname: String, val lastname: String)

@Serializable
data class CharacterAgeDTO(val age: Int)
