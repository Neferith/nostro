package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequirement
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

object Intidimidation: Skill {
    override val id = "intimidation"
    override val name = "Intidimidation"
    override val description = "Permet d'intimidier les PNJ"
    override val type = SkillType.SOCIAL

    override val required: AttributeRequirement = AttributeRequirement(
        brain = 2,
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 0,
        flexibility = 0,
        brain = 1,
        vitality = 0
    )
}