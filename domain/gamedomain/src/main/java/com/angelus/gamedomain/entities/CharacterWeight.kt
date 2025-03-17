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

fun CharacterWeight.weightToSensitivity(): List<CharacterSensitivity> {

    when(this) {
        CharacterWeight.LIGHT -> return listOf(
            CharacterSensitivity.HYPERSENSITIVE,
            CharacterSensitivity.HIGH_SENSITIVITY,
        )
        CharacterWeight.AVERAGE ->  return listOf(
            CharacterSensitivity.HIGH_SENSITIVITY,
            CharacterSensitivity.NORMAL,
            CharacterSensitivity.LOW_SENSITIVITY,
        )
        CharacterWeight.HEAVY -> return listOf(
            CharacterSensitivity.HIGH_SENSITIVITY,
            CharacterSensitivity.NORMAL,
            CharacterSensitivity.LOW_SENSITIVITY,
        )
        CharacterWeight.VERY_HEAVY -> return listOf(
            CharacterSensitivity.NORMAL,
            CharacterSensitivity.LOW_SENSITIVITY,
        )
        CharacterWeight.EXTREME -> return listOf(
            CharacterSensitivity.LOW_SENSITIVITY,
            CharacterSensitivity.INSENSITIVE,
        )
    }
}