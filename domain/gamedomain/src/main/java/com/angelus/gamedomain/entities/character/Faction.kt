package com.angelus.gamedomain.entities.character

// Enum pour représenter les relations entre factions
enum class Relation {
    ALLIED,     // Alliés (80-100)
    FRIENDLY,   // Amicaux (60-79)
    NEUTRAL,    // Neutres (40-59)
    WARY,       // Méfiants (20-39)
    HOSTILE     // Hostiles (0-19)
}

// Classe abstraite représentant une faction
data class Faction(
    val id: String,
    val name: String,
    val description: String,
    val relations: Map<String, Int> = emptyMap()
) {

    fun setRelation(faction: Faction, value: Int): Faction {
        val updated = relations + (faction.id to value.coerceIn(0, 100))
        return copy(relations = updated)
    }

    fun adjustRelation(faction: Faction, delta: Int): Faction {
        val current = relations[faction.id] ?: 50
        val updatedValue = (current + delta).coerceIn(0, 100)
        return copy(relations = relations + (faction.id to updatedValue))
    }

    fun describeRelations() {
        println("$name Relations :")
        relations.forEach { (factionId, value) ->
            println("  - $factionId: ${getRelationType(value)} ($value)")
        }
    }

    private fun getRelationType(value: Int): Relation {
        return when {
            value >= 80 -> Relation.ALLIED
            value >= 60 -> Relation.FRIENDLY
            value >= 40 -> Relation.NEUTRAL
            value >= 20 -> Relation.WARY
            else -> Relation.HOSTILE
        }
    }
}

/*class PlayerFaction() :
    Faction(
        Character.PLAYER_FACTION,
        Character.PLAYER_FACTION,
        "description"
    ) {

}*/