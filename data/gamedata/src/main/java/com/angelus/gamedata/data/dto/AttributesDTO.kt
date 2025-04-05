package com.angelus.gamedata.data.dto

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
