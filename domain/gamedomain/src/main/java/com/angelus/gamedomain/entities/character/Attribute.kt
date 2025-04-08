package com.angelus.gamedomain.entities.character

import kotlin.math.sign

data class Attribute(
    val permanent: Int,
    val min: Int = -10,
    val max: Int = +10,) {

}

data class Attributes(
    val musculature: Attribute,
    val flexibility: Attribute,
    val brain: Attribute,
    val vitality: Attribute
) {
    companion object {
        val Empty = Attributes(
            musculature = Attribute(0),
            flexibility = Attribute(0),
            brain = Attribute(0),
            vitality = Attribute(0)
        )

        private fun randomBetweenZeroAnd(value: Int): Int {
            return if (value == 0) 0
            else kotlin.random.Random.nextInt(0, kotlin.math.abs(value) + 1) * value.sign
        }
    }

    fun applyModifier(modifier: AttributesModifier): Attributes {
        return Attributes(
            musculature = Attribute( permanent = musculature.permanent + randomBetweenZeroAnd(modifier.musculature)),
            flexibility = Attribute( permanent = flexibility.permanent + randomBetweenZeroAnd(modifier.flexibility)),
            brain = Attribute( permanent = brain.permanent + randomBetweenZeroAnd(modifier.brain)), //brain + modifier.brain,
            vitality = Attribute( permanent = vitality.permanent + randomBetweenZeroAnd(modifier.vitality)), //vitality + modifier.vitality
        )
    }



}

data class AttributeRequirement(
    val musculature: Int? = null,
    val flexibility: Int? = null,
    val brain: Int? = null,
    val vitality: Int? = null
)

data class AttributesModifier(
    val musculature: Int,
    val flexibility: Int,
    val brain: Int,
    val vitality: Int
) {
    operator fun plus(other: AttributesModifier): AttributesModifier {
        return AttributesModifier(
            musculature = this.musculature + other.musculature,
            flexibility = this.flexibility + other.flexibility,
            brain = this.brain + other.brain,
            vitality = this.vitality + other.vitality
        )
    }

}