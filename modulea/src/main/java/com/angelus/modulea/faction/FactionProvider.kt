package com.angelus.modulea.faction

import com.angelus.gamedomain.entities.character.Character
import com.angelus.gamedomain.entities.character.Faction

interface FactionProvider {

    val id: String
    val name: String
    val description: String
    val relations: Map<String, Int>

    fun createFaction(): Faction {
        return Faction(
            id = id,
            name = name,
            description = description,
            relations = relations
        )
    }

}