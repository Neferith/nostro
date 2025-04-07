package com.angelus.gamedata.data.mapper

import com.angelus.gamedata.data.dto.AttributeDTO
import com.angelus.gamedata.data.dto.AttributeRequirementDTO
import com.angelus.gamedata.data.dto.AttributesDTO
import com.angelus.gamedata.data.dto.AttributesModifierDTO
import com.angelus.gamedomain.entities.character.Attribute
import com.angelus.gamedomain.entities.character.AttributeRequirement
import com.angelus.gamedomain.entities.character.Attributes
import com.angelus.gamedomain.entities.character.AttributesModifier


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