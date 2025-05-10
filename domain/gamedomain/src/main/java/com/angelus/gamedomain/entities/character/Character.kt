package com.angelus.gamedomain.entities.character

import com.angelus.gamedomain.entities.item.Inventory
import com.angelus.gamedomain.entities.item.addItem
import com.angelus.gamedomain.entities.item.removeItem

data class CharacterName(val firstname: String, val lastname: String)

fun CharacterName.fullName(): String {
    return this.firstname + " " + this.lastname
}



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
    val id: String,
    val mainAttributes: Attributes,
    val characterLevel: CharacterLevel,
    val description: CharacterDescription,
    val skills: CharacterSkills,

    val inventory: Inventory

) {
    companion object {
        val MAIN_CHARACTER = "MAIN_CHARACTER"
    }

}

fun Character.withItemAdded(itemId: String, quantity: Int = 1): Character {
    val newInventory = (inventory ?: Inventory()).addItem(itemId, quantity)
    return copy(inventory = newInventory)
}

fun Character.withItemRemoved(itemId: String, quantity: Int = 1): Character {
    val updatedInventory = inventory?.removeItem(itemId, quantity) ?: return this
    return copy(inventory = updatedInventory)
}