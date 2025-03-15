package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequierment
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

object HolyEnergy: Skill {
    override val id = "holyEnergy"
    override val name = "holyEnergy"
    override val description = "Energie divine"
    override val type = SkillType.MAGIC

    override val required: AttributeRequierment = AttributeRequierment(
        brain = 2,
        vitality = 2
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 0,
        flexibility = 0,
        brain = 0,
        vitality = 0
    )
}