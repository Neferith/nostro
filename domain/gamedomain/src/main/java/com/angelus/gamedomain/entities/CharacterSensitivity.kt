package com.angelus.gamedomain.entities

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