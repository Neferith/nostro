package com.angelus.npcdomain.entities

// Enum pour représenter les relations entre factions
enum class Relation {
    ALLIED,     // Alliés (80-100)
    FRIENDLY,   // Amicaux (60-79)
    NEUTRAL,    // Neutres (40-59)
    WARY,       // Méfiants (20-39)
    HOSTILE     // Hostiles (0-19)
}

// Classe abstraite représentant une faction
abstract class Faction(
    val name: String,
    val description: String
) {
    // Relations avec les autres factions, stockées sous forme d'un score entre 0 et 100
    private val relations: MutableMap<Faction, Int> = mutableMapOf()

    // Définition initiale d'une relation (valeur entre 0 et 100)
    fun setRelation(faction: Faction, value: Int) {
        relations[faction] = value.coerceIn(0, 100)
    }

    // Ajuste progressivement une relation (ex : +10 ou -15)
    fun adjustRelation(faction: Faction, delta: Int) {
        val newValue = (relations[faction] ?: 50) + delta
        relations[faction] = newValue.coerceIn(0, 100)
    }

    // Affiche les relations de la faction sous forme de statut
    fun describeRelations() {
        println("$name Relations :")
        relations.forEach { (faction, value) ->
            println("  - ${faction.name}: ${getRelationType(value)} ($value)")
        }
    }

    // Convertit une valeur en un type de relation
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