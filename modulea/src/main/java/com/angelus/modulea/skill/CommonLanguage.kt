package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.character.AttributeRequirement
import com.angelus.gamedomain.entities.character.AttributesModifier
import com.angelus.gamedomain.entities.character.Skill
import com.angelus.gamedomain.entities.character.SkillType

object CommonLanguage : Skill {
    override val id = "commonLanguage"
    override val name = "commonLanguage"
    override val type = SkillType.KNOWNESS
    override val description = "Permet de communique en langue commune."

    override val required: AttributeRequirement = AttributeRequirement(
        brain = -5,
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 0,
        flexibility = 0,
        brain = 1,
        vitality = 0
    )
}