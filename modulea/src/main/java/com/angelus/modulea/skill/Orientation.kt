package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequirement
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

object Orientation : Skill {
    override val id = "orientation"
    override val name = "orientation"
    override val description = "Permet de vous orienter"
    override val type = SkillType.TECHNICAL

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