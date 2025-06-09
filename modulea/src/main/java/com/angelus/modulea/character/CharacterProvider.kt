package com.angelus.modulea.character

import com.angelus.gamedomain.entities.character.Attributes
import com.angelus.gamedomain.entities.character.Character
import com.angelus.gamedomain.entities.character.CharacterDescription
import com.angelus.gamedomain.entities.character.CharacterLevel
import com.angelus.gamedomain.entities.character.CharacterSkills
import com.angelus.gamedomain.entities.item.Inventory

interface CharacterProvider {
    val id: String
    val factionId: String
    val mainAttributes: Attributes
    val characterLevel: CharacterLevel
    val description: CharacterDescription
    val skills: CharacterSkills
    val inventory: Inventory

    fun createCharacter(): Character {
        return Character(
            id = id,
            factionId = factionId,
            mainAttributes = mainAttributes.copy(), // Deep copy si nécessaire
            characterLevel = characterLevel.copy(),
            description = description.copy(),
            skills = skills.copy(),
            inventory = inventory.copy()
        )
    }
}
