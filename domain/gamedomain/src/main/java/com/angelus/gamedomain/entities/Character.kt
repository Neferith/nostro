package com.angelus.gamedomain.entities


data class Attribute(val min: Int, val max: Int, val permanent: Int) {

}

data class Attributes(
    val musculature: Attribute,
    val flexibility: Attribute,
    val brain: Attribute,
    val vitality: Attribute
) {


}

data class AttributesModifier(
    val musculature: Int,
    val flexibility: Int,
    val brain: Int,
    val vitality: Int
) {


}

enum class CharacterSize(val minHeightCm: Int, val maxHeightCm: Int, modifier: AttributesModifier) {
    DWARF(
        130,
        150,
        AttributesModifier(
            musculature = +1,
            flexibility = -2,
            brain = 0,
            vitality = +1
        )
    ),
    SMALL(
        150, 165,
        AttributesModifier(
            musculature = -2,
            flexibility = +2,
            brain = 0,
            vitality = +1
        )
    ),
    MEDIUM(
        165, 185,
        AttributesModifier(
            musculature = -1,
            flexibility = +1,
            brain = 0,
            vitality = 0
        )
    ),
    TALL(
        185, 200,
        AttributesModifier(
            musculature = +1,
            flexibility = -1,
            brain = 0,
            vitality = 0
        )
    ),
    LARGE(
        200, 220,
        AttributesModifier(
            musculature = +1,
            flexibility = -2,
            brain = 0,
            vitality = +1
        )
    ),
    GIANT(
        220, 250,
        AttributesModifier(
            musculature = +2,
            flexibility = -4,
            brain = 0,
            vitality = +2
        )
    )
}

enum class CharacterWeight(
    val minWeightKg: Int,
    val maxWeightKg: Int,
    val modifier: AttributesModifier
) {
    LIGHT(
        40, 60,
        AttributesModifier(
            musculature = -2,
            flexibility = +2,
            brain = 0,
            vitality = -1
        )
    ),
    AVERAGE(
        60, 85,
        AttributesModifier(
            musculature = 0,
            flexibility = 0,
            brain = 0,
            vitality = 0
        )
    ),
    HEAVY(
        85, 110,
        AttributesModifier(
            musculature = +2,
            flexibility = -1,
            brain = 0,
            vitality = +1
        )
    ),
    VERY_HEAVY(
        110, 140,
        AttributesModifier(
            musculature = +4,
            flexibility = -2,
            brain = 0,
            vitality = +2
        )
    ),
    EXTREME(
        140, 200,
        AttributesModifier(
            musculature = +6,
            flexibility = -3,
            brain = 0,
            vitality = +3
        )
    )
}

val sizeToWeightMap: Map<CharacterSize, List<CharacterWeight>> = mapOf(
    CharacterSize.DWARF to listOf(CharacterWeight.LIGHT, CharacterWeight.AVERAGE),
    CharacterSize.SMALL to listOf(
        CharacterWeight.LIGHT,
        CharacterWeight.AVERAGE,
        CharacterWeight.HEAVY
    ),
    CharacterSize.MEDIUM to listOf(
        CharacterWeight.LIGHT,
        CharacterWeight.AVERAGE,
        CharacterWeight.HEAVY,
        CharacterWeight.VERY_HEAVY
    ),
    CharacterSize.TALL to listOf(
        CharacterWeight.AVERAGE,
        CharacterWeight.HEAVY,
        CharacterWeight.VERY_HEAVY
    ),
    CharacterSize.LARGE to listOf(
        CharacterWeight.HEAVY,
        CharacterWeight.VERY_HEAVY,
        CharacterWeight.EXTREME
    ),
    CharacterSize.GIANT to listOf(CharacterWeight.VERY_HEAVY, CharacterWeight.EXTREME)
)

enum class CharacterSensitivity(
    val modifier: AttributesModifier
) {
    INSENSITIVE(
        AttributesModifier(
            musculature = +1,   // Très résistant physiquement
            flexibility = +0,   // Aucun changement sur la souplesse
            brain = -2,         // Moins de réflexion émotionnelle, tendance à être insensible ou sociopathe
            vitality = +2       // Très résistant aux douleurs physiques
        )
    ),
    LOW_SENSITIVITY(
        AttributesModifier(
            musculature = +0,   // Sensibilité faible, mais toujours une certaine résistance
            flexibility = +0,   // Aucun changement sur la souplesse
            brain = -1,         // Moins empathique, mais un peu plus réfléchi que l'insensible
            vitality = +1       // Capacité de résistance physique un peu meilleure
        )
    ),
    NORMAL(
        AttributesModifier(
            musculature = 0,    // Pas de modification
            flexibility = 0,    // Pas de modification
            brain = 0,          // Sensibilité classique, capacités sociales et réflexion équilibrées
            vitality = 0        // Aucune modification, personne moyenne
        )
    ),
    HIGH_SENSITIVITY(
        AttributesModifier(
            musculature = -1,   // Moins capable de résister à la douleur physique
            flexibility = -1,   // Plus fragile, moins souple en raison de la sensibilité
            brain = +1,         // Plus de réflexion, empathie, et compréhension émotionnelle (intelligence sociale)
            vitality = -1       // Moins résistant physiquement aux coups
        )
    ),
    HYPERSENSITIVE(
        AttributesModifier(
            musculature = -2,   // Très faible résistance à la douleur
            flexibility = -2,   // Très sensible, ce qui diminue la flexibilité physique
            brain = +2,         // Très réfléchi, empathique et très intelligent socialement (mais peut être fragile émotionnellement)
            vitality = -2       // Très faible résistance physique
        )
    )
}

data class Background(
    val id: String,
    val description: String,  // Description de l'enfance du personnage
    val attributesModifier: AttributesModifier,
    val startingSkills: List<String> //
)


data class Skill(val name: String, val level: Int)

data class CharacterSkills(val skills: List<Skill>) {

}

data class Description(
    val size: CharacterSize,
    val weight: CharacterWeight,
    val sensitivity: CharacterSensitivity,
    val background: List<Background>,

) {


}

data class Character(
    val mainAttributes: Attributes,
    val description: Description,
    val skills: CharacterSkills
) {
}