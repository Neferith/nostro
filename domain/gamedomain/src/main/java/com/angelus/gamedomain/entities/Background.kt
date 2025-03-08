package com.angelus.gamedomain.entities

data class Background(
    val id: String,
    val description: String,  // Description de l'enfance du personnage
    val attributesModifier: AttributesModifier,
    val startingSkills: List<String> //
)