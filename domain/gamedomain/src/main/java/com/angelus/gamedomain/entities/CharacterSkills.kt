package com.angelus.gamedomain.entities

enum class SkillType {
    WEAPON,
    DISTANCE,
    DEFENSE,
    SOCIAL,
    TECHNICAL,
    KNOWNESS,
    MAGIC
}
interface Skill {
    val id: String
    val name: String
    val type: SkillType
    val description: String
    val required: AttributeRequirement
    var modifier: AttributesModifier
}

data class CharacterSkill(val skillId: String, val level: Int)

data class CharacterSkills(val skills: Map<String, CharacterSkill>) {

    fun addSkill(newSkill: CharacterSkill): CharacterSkills {
        return this.copy(skills = skills + (newSkill.skillId to newSkill))
    }

    fun getSkillById(id: String): CharacterSkill? = skills[id]
}