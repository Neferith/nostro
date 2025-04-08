package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.character.AttributeRequirement
import com.angelus.gamedomain.entities.character.AttributesModifier
import com.angelus.gamedomain.entities.character.Skill
import com.angelus.gamedomain.entities.character.SkillType

object EvilEnergy: Skill {
    override val id = "evilEnergy"
    override val name = "evilEnergy"
    override val description = "Energie maléfique"
    override val type = SkillType.MAGIC

    override val required: AttributeRequirement = AttributeRequirement(
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