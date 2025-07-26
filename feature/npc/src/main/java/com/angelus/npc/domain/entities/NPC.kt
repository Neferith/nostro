package com.angelus.npc.domain.entities

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.character.Character

data class NPC (
    val character: Character,
    val entityPosition: EntityPosition
)