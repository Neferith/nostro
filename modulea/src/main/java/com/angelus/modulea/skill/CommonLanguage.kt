package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequirement
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

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