package com.angelus.gamedomain.entities

enum class CharacterSize(
    val minHeightCm: Int,
    val maxHeightCm: Int,
    val modifier: AttributesModifier
) {
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