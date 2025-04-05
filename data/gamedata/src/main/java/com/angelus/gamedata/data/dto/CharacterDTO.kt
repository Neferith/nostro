package com.angelus.gamedata.data.dto

import com.angelus.gamedomain.entities.Character
import com.angelus.gamedomain.entities.CharacterAge
import com.angelus.gamedomain.entities.CharacterDescription
import com.angelus.gamedomain.entities.CharacterGender
import com.angelus.gamedomain.entities.CharacterLevel
import com.angelus.gamedomain.entities.CharacterName
import com.angelus.gamedomain.entities.CharacterSensitivity
import com.angelus.gamedomain.entities.CharacterSize
import com.angelus.gamedomain.entities.CharacterWeight
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDTO(
    val mainAttributes: AttributesDTO,
    val characterLevel: CharacterLevelDTO,
    val description: CharacterDescriptionDTO,
    val skills: CharacterSkillsDTO
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
