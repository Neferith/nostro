package com.angelus.gamedomain.entities

data class Description(
    val gender: CharacterGender,
    val size: CharacterSize,
    val weight: CharacterWeight,
    val sensitivity: CharacterSensitivity,
    val background: List<Background>,
)

data class Character(
    val mainAttributes: Attributes,
    val description: Description,
    val skills: CharacterSkills
)