package com.angelus.gamedomain.entities.character

enum class CharacterGender(
    val modifier: AttributesModifier
) {
    MALE(
        AttributesModifier(
        musculature = +1,
        flexibility = 0,
        brain = 0,
        vitality = +1,
    )
    ),
    FEMALE(
        AttributesModifier(
        musculature = 0,
        flexibility = +2,
        brain = 0,
        vitality = 0,
    )
    )
}

fun CharacterGender.genderToSizeMap(): List<CharacterSize> {

    when(this) {
        CharacterGender.MALE -> {
            return CharacterSize.values().toList()
        }
        CharacterGender.FEMALE -> {
            return listOf(
                CharacterSize.DWARF,
                CharacterSize.SMALL,
                CharacterSize.MEDIUM,
                CharacterSize.TALL
            )
        }
    }
}