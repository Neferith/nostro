package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequierment
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

object Acrobaty : Skill {
    override val id = "climbing"
    override val name = "Escalade"
    override val type = SkillType.TECHNICAL
    override val description = "Permet de grimper sur certains obstacles."

    override val required: AttributeRequierment = AttributeRequierment(
        musculature = 1,
        flexibility = 1,
    )

    override var modifier: AttributesModifier= AttributesModifier(
        musculature = 1,
        flexibility = 1,
        brain = 0,
        vitality = 0
    )
}