package com.angelus.npc.domain.mock

import com.angelus.gamedomain.entities.Position
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


val mockCharacterId1 = "CHARACTER_MOCK_1"

val mockCharacterPosition = Position(
   x = 45, y = 45
)
val characterMock1 = Character(
    id = mockCharacterId1,
    factionId = "FACTION_MOCK_1",
    mainAttributes = Attributes.Empty,
    characterLevel = CharacterLevel(0,0),
    description = CharacterDescription(
        name = CharacterName(
            firstname = "NAME",
            lastname = "NAME"
        ),
        age = CharacterAge(45),
        gender = CharacterGender.MALE,
        size = CharacterSize.MEDIUM,
        weight = CharacterWeight.AVERAGE,
        sensitivity = CharacterSensitivity.NORMAL,
        background = emptyList()
    ),
    skills = CharacterSkills(
        skills = emptyMap()
    ),
    inventory = Inventory()
)

val characterMock2 = Character(
    id = "CHARACTER_MOCK_2",
    factionId = "FACTION_MOCK_1",
    mainAttributes = Attributes.Empty,
    characterLevel = CharacterLevel(0,0),
    description = CharacterDescription(
        name = CharacterName(
            firstname = "NAME",
            lastname = "NAME"
        ),
        age = CharacterAge(45),
        gender = CharacterGender.MALE,
        size = CharacterSize.MEDIUM,
        weight = CharacterWeight.AVERAGE,
        sensitivity = CharacterSensitivity.NORMAL,
        background = emptyList()
    ),
    skills = CharacterSkills(
        skills = emptyMap()
    ),
    inventory = Inventory()
)