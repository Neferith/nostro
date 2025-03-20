package com.angelus.gamedata.data

import com.angelus.gamedomain.entities.Attribute
import com.angelus.gamedomain.entities.AttributeRequirement
import com.angelus.gamedomain.entities.Attributes
import com.angelus.gamedomain.entities.AttributesModifier
import kotlinx.serialization.Serializable

@Serializable
data class AttributesModifierDTO(
    val musculature: Int,
    val flexibility: Int,
    val brain: Int,
    val vitality: Int
)

@Serializable
data class AttributeRequirementDTO(
    val musculature: Int? = null,
    val flexibility: Int? = null,
    val brain: Int? = null,
    val vitality: Int? = null
)

@Serializable
data class AttributesDTO(
    val musculature: AttributeDTO,
    val flexibility: AttributeDTO,
    val brain: AttributeDTO,
    val vitality: AttributeDTO,
)

@Serializable
data class AttributeDTO(
    val permanent: Int, val min: Int, val max: Int
)


fun AttributesModifier.convertToDTO(): AttributesModifierDTO {
    return AttributesModifierDTO(
        musculature = this.musculature,
        flexibility = this.flexibility,
        brain = this.brain,
        vitality = this.vitality
    )
}

fun AttributesModifierDTO.convertFromDTO(): AttributesModifier {

    return AttributesModifier(
        musculature = this.musculature,
        flexibility = this.flexibility,
        brain = this.brain,
        vitality = this.vitality
    )
}

fun AttributeRequirement.convertToDTO(): AttributeRequirementDTO {
    return AttributeRequirementDTO(
        musculature = this.musculature,
        flexibility = this.flexibility,
        brain = this.brain,
        vitality = this.vitality
    )
}

fun AttributeRequirementDTO.convertFromDTO(): AttributeRequirement {

    return AttributeRequirement(
        musculature = this.musculature,
        flexibility = this.flexibility,
        brain = this.brain,
        vitality = this.vitality
    )
}

fun Attribute.convertAttributesToDTO(): AttributeDTO {
    return AttributeDTO(
        permanent = this.permanent,
        min = this.min,
        max = this.max
    )
}

fun AttributeDTO.convertAttributesFromDTO(): Attribute {

    return Attribute(
        permanent = this.permanent,
        min = this.min,
        max = this.max
    )
}

fun Attributes.convertAttributesToDTO(): AttributesDTO {
    return AttributesDTO(
        musculature = this.musculature.convertAttributesToDTO(),
        flexibility = this.flexibility.convertAttributesToDTO(),
        brain = this.brain.convertAttributesToDTO(),
        vitality = this.vitality.convertAttributesToDTO()
    )
}

fun AttributesDTO.convertAttributesFromDTO(): Attributes {

    return Attributes(
        musculature = this.musculature.convertAttributesFromDTO(),
        flexibility = this.flexibility.convertAttributesFromDTO(),
        brain = this.brain.convertAttributesFromDTO(),
        vitality = this.vitality.convertAttributesFromDTO()
    )
}