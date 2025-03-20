package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequirement
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

object Reading : Skill {
    override val id = "reading"
    override val name = "reading"
    override val description = "Permet de lire."
    override val type = SkillType.KNOWNESS

    override val required: AttributeRequirement = AttributeRequirement(
        brain = 0,
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 0,
        flexibility = 0,
        brain = 1,
        vitality = 0
    )
}