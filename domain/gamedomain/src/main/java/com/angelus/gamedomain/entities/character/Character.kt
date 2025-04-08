package com.angelus.gamedomain.entities.character

import com.angelus.gamedomain.entities.item.Inventory

data class CharacterName(val firstname: String, val lastname: String)



data class CharacterDescription(
    val name: CharacterName,
    val age: CharacterAge = CharacterAge(20),
    val gender: CharacterGender,
    val size: CharacterSize,
    val weight: CharacterWeight,
    val sensitivity: CharacterSensitivity,
    val background: List<String>,
)

data class Character(
    val mainAttributes: Attributes,
    val characterLevel: CharacterLevel,
    val description: CharacterDescription,
    val skills: CharacterSkills,

    val inventory: Inventory
)