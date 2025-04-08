package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.character.AttributeRequirement
import com.angelus.gamedomain.entities.character.AttributesModifier
import com.angelus.gamedomain.entities.character.Skill
import com.angelus.gamedomain.entities.character.SkillType

object Acrobaty : Skill {
    override val id = "climbing"
    override val name = "Escalade"
    override val type = SkillType.TECHNICAL
    override val description = "Permet de grimper sur certains obstacles."

    override val required: AttributeRequirement = AttributeRequirement(
        musculature = 1,
        flexibility = 1,
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 1,
        flexibility = 1,
        brain = 0,
        vitality = 0
    )
}