package com.angelus.gamedata.data.dto

import com.angelus.gamedomain.entities.CharacterSkill
import com.angelus.gamedomain.entities.CharacterSkills
import kotlinx.serialization.Serializable

@Serializable
data class CharacterSkillDTO (
    val skillId: String, val level: Int
)

@Serializable
data class CharacterSkillsDTO(val skills: Map<String, CharacterSkillDTO>)
