package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequierment
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

object Survival : Skill {
    override val id = "Survival"
    override val name = "Survival"
    override val description = "Permet de survivre"
    override val type = SkillType.TECHNICAL

    override val required: AttributeRequierment = AttributeRequierment(
        vitality = 2
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 0,
        flexibility = 0,
        brain = 0,
        vitality = 1
    )
}