package com.angelus.modulea.character

import com.angelus.gamedomain.entities.character.Attribute
import com.angelus.gamedomain.entities.character.Attributes
import com.angelus.gamedomain.entities.character.CharacterAge
import com.angelus.gamedomain.entities.character.CharacterDescription
import com.angelus.gamedomain.entities.character.CharacterGender
import com.angelus.gamedomain.entities.character.CharacterLevel
import com.angelus.gamedomain.entities.character.CharacterName
import com.angelus.gamedomain.entities.character.CharacterSensitivity
import com.angelus.gamedomain.entities.character.CharacterSize
import com.angelus.gamedomain.entities.character.CharacterSkills
import com.angelus.gamedomain.entities.character.CharacterWeight
import com.angelus.gamedomain.entities.item.Inventory
import com.angelus.modulea.faction.Monster

object Goblin : CharacterProvider{
    override val id: String = "Goblin"
    override val factionId: String = Monster.id
    override val mainAttributes: Attributes = Attributes(
        musculature = Attribute(
            permanent = 1,
            min = -10,
            max = 10
        ),
        flexibility = Attribute(
            permanent = 3,
            min = -10,
            max = 10
        ),
        brain = Attribute(
            permanent = -1,
            min = -10,
            max = 10
        ),
        vitality = Attribute(
            permanent = 0,
            min = -10,
            max = 10
        )
    )
    override val characterLevel: CharacterLevel
        get() = CharacterLevel(
            level = 1,
            experience = 250
        )
    override val description: CharacterDescription = CharacterDescription(
        name = CharacterName(
            firstname = "Goblin",
            lastname = "Goblin"
        ),
        age = CharacterAge(0),
        gender = CharacterGender.MALE,
        size = CharacterSize.SMALL,
        weight = CharacterWeight.LIGHT,
        sensitivity = CharacterSensitivity.NORMAL,
        background = emptyList()
    )
    override val skills: CharacterSkills = CharacterSkills(
        skills = emptyMap()
    )
    override val inventory: Inventory = Inventory()

}