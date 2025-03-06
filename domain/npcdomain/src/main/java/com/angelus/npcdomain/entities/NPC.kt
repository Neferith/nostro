package com.angelus.npcdomain.entities

import android.icu.text.Transliterator.Position
import com.angelus.gamedomain.entities.EntityPosition

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