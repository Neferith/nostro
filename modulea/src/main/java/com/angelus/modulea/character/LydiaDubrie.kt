package com.angelus.modulea.character

import com.angelus.gamedomain.entities.character.Attribute
import com.angelus.gamedomain.entities.character.Attributes
import com.angelus.gamedomain.entities.character.Character
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
import com.angelus.modulea.faction.Chaoteux

object LydiaDubrie: CharacterProvider{
    override val id: String = "LydiaDubrie"
    override val factionId: String = Chaoteux.id
    override val mainAttributes: Attributes = Attributes(
        musculature = Attribute(
            permanent = -3,
            min = TODO(),
            max = TODO()
        ),
        flexibility = Attribute(
            permanent = +4,
            min = TODO(),
            max = TODO()
        ),
        brain = Attribute(
            permanent = 0,
            min = TODO(),
            max = TODO()
        ),
        vitality = Attribute(
            permanent = -1,
            min = TODO(),
            max = TODO()
        )
    )
    override val characterLevel: CharacterLevel
        get() = TODO("Not yet implemented")
    override val description: CharacterDescription = CharacterDescription(
        name = CharacterName(
            firstname = "Lydia",
            lastname = "Dubrie"
        ),
        age = CharacterAge(23),
        gender = CharacterGender.FEMALE,
        size = CharacterSize.SMALL,
        weight = CharacterWeight.LIGHT,
        sensitivity = CharacterSensitivity.HIGH_SENSITIVITY,
        background = emptyList()
    )
    override val skills: CharacterSkills
        get() = TODO("Not yet implemented")
    override val inventory: Inventory
        get() = TODO("Not yet implemented")

}