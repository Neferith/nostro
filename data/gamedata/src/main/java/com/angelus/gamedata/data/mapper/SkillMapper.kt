package com.angelus.gamedata.data.mapper

import com.angelus.gamedata.data.dto.CharacterSkillDTO
import com.angelus.gamedata.data.dto.CharacterSkillsDTO
import com.angelus.gamedomain.entities.CharacterSkill
import com.angelus.gamedomain.entities.CharacterSkills


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
