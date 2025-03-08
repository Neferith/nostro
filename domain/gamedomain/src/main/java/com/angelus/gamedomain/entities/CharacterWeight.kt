package com.angelus.gamedomain.entities

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
    CharacterSize.GIANT to listOf(
        CharacterWeight.VERY_HEAVY,
        CharacterWeight.EXTREME
    )
)