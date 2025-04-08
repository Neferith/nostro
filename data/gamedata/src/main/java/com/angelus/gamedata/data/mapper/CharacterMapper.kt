package com.angelus.gamedata.data.mapper

import com.angelus.gamedata.data.dto.CharacterAgeDTO
import com.angelus.gamedata.data.dto.CharacterDTO
import com.angelus.gamedata.data.dto.CharacterDescriptionDTO
import com.angelus.gamedata.data.dto.CharacterLevelDTO
import com.angelus.gamedata.data.dto.CharacterNameDTO
import com.angelus.gamedomain.entities.character.Character
import com.angelus.gamedomain.entities.character.CharacterAge
import com.angelus.gamedomain.entities.character.CharacterDescription
import com.angelus.gamedomain.entities.character.CharacterGender
import com.angelus.gamedomain.entities.character.CharacterLevel
import com.angelus.gamedomain.entities.character.CharacterName
import com.angelus.gamedomain.entities.character.CharacterSensitivity
import com.angelus.gamedomain.entities.character.CharacterSize
import com.angelus.gamedomain.entities.character.CharacterWeight


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