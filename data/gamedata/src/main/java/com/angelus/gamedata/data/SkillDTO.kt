package com.angelus.gamedata.data

import com.angelus.gamedomain.entities.CharacterSkill
import com.angelus.gamedomain.entities.CharacterSkills

data class CharacterSkillDTO (
    val skillId: String, val level: Int
)

fun CharacterSkill.convertToDTO(): CharacterSkillDTO {
    return CharacterSkillDTO(
        skillId = this.skillId,
        level = this.level
    )
}

fun CharacterSkillDTO.convertFromDTO(): CharacterSkill {
    return CharacterSkill(
        skillId = this.skillId,
        level = this.level
    )
}

data class CharacterSkillsDTO(val skills: Map<String, CharacterSkillDTO>)

fun CharacterSkills.convertToDTO(): CharacterSkillsDTO {
    return CharacterSkillsDTO(
        skills = this.skills.mapValues  { entry -> entry.value.convertToDTO() }
    )
}

fun CharacterSkillsDTO.convertFromDTO(): CharacterSkills {

    return CharacterSkills(
        skills = this.skills.mapValues  { entry -> entry.value.convertFromDTO() }
    )
}
