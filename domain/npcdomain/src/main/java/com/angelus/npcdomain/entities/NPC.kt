package com.angelus.npcdomain.entities

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.character.Faction

// Enum pour représenter les relations entre factions
enum class NPCType {
    CUSTOM,
    GOBLIN
}


data class NPC(val id: String,
               var entityPosition: EntityPosition,
               val faction: Faction,
               val type: NPCType) {
}