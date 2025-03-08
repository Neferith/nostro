package com.angelus.gamedomain.entities

enum class CharacterGender(
    val modifier: AttributesModifier
) {
    MALE(AttributesModifier(
        musculature = +1,
        flexibility = 0,
        brain = 0,
        vitality = +1,
    )),
    FEMALE(AttributesModifier(
        musculature = 0,
        flexibility = +2,
        brain = 0,
        vitality = 0,
    ))
}