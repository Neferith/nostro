package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.character.AttributeRequirement
import com.angelus.gamedomain.entities.character.AttributesModifier
import com.angelus.gamedomain.entities.character.Skill
import com.angelus.gamedomain.entities.character.SkillType

object Survival : Skill {
    override val id = "Survival"
    override val name = "Survival"
    override val description = "Permet de survivre"
    override val type = SkillType.TECHNICAL

    override val required: AttributeRequirement = AttributeRequirement(
        vitality = 2
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 0,
        flexibility = 0,
        brain = 0,
        vitality = 1
    )
}