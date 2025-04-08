package com.angelus.gamedomain.entities.character

interface Background {
    val id: String
    val description: String  // Description de l'enfance du personnage
    val attributesModifier: AttributesModifier
    val startingSkills: List<Skill> //
    val requirement: BackgroundRequirement?
}

interface BackgroundType {
    val id: String
    val name: String
    val description: String
    val backgrounds: List<Background>

}

data class BackgroundRequirement(val characterSize: List<CharacterSize>?)