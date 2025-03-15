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

data class AttributeRequierment(
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