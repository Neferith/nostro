package com.angelus.gamedomain.entities

data class CharacterName(val firstname: String, val lastname: String)



data class CharacterDescription(
    val name: CharacterName,
    val age: CharacterAge = CharacterAge(20),
    val gender: CharacterGender,
    val size: CharacterSize,
    val weight: CharacterWeight,
    val sensitivity: CharacterSensitivity,
    val background: List<Background>,
)

data class Character(
    val mainAttributes: Attributes,
    val characterLevel: CharacterLevel,
    val description: CharacterDescription,
    val skills: CharacterSkills
)