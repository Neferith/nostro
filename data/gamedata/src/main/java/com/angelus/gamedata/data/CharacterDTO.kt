package com.angelus.gamedata.data

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

data class CharacterNameDTO(val firstname: String, val lastname: String)

data class CharacterAgeDTO(val age: Int)

fun CharacterDescription.convertToDTO(): CharacterDescriptionDTO {
    return CharacterDescriptionDTO(
        name = this.name.convertCharacterToDTO(),
        age = this.age.convertCharacterToDTO(),
        gender = this.gender.toString(),
        size = this.size.toString(),
        weight = this.weight.toString(),
        sensitivity = this.sensitivity.toString(),
        background = this.background
    )
}

fun CharacterDescriptionDTO.convertFromDTO(): CharacterDescription {
    return CharacterDescription(
        name = this.name.convertCharacterFromDTO(),
        age = this.age.convertCharacterFromDTO(),
        gender = CharacterGender.valueOf(this.gender),
        size = CharacterSize.valueOf(this.size),
        weight = CharacterWeight.valueOf(this.weight),
        sensitivity = CharacterSensitivity.valueOf(this.sensitivity),
        background = this.background
    )
}

fun Character.convertCharacterToDTO(): CharacterDTO {
    return CharacterDTO(
        mainAttributes = this.mainAttributes.convertAttributesToDTO(),
        characterLevel = this.characterLevel.convertCharacterLevelToDTO(),
        description = this.description.convertToDTO(),
        skills = this.skills.convertToDTO()
    )
}

fun CharacterDTO.convertCharacterFromDTO(): Character {
    return Character(
        mainAttributes = this.mainAttributes.convertAttributesFromDTO(),
        characterLevel = this.characterLevel.convertCharacterLevelFromDTO(),
        description = this.description.convertFromDTO(),
        skills = this.skills.convertFromDTO()
    )
}

fun CharacterLevel.convertCharacterLevelToDTO(): CharacterLevelDTO {
    return CharacterLevelDTO(
        level = this.level,
        experience = this.experience
    )

}

fun CharacterLevelDTO.convertCharacterLevelFromDTO(): CharacterLevel {
    return CharacterLevel(
        level = this.level,
        experience = this.experience
    )
}

fun CharacterAge.convertCharacterToDTO(): CharacterAgeDTO {
    return CharacterAgeDTO(
        age = this.age
    )
}

fun CharacterAgeDTO.convertCharacterFromDTO(): CharacterAge {
    return CharacterAge(
        age = this.age
    )
}


fun CharacterName.convertCharacterToDTO(): CharacterNameDTO {
    return CharacterNameDTO(
        firstname = this.firstname,
        lastname = this.lastname
    )
}

fun CharacterNameDTO.convertCharacterFromDTO(): CharacterName {
    return CharacterName(
        firstname = this.firstname,
        lastname = this.lastname
    )
}