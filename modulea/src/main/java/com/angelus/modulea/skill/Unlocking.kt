package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.character.AttributeRequirement
import com.angelus.gamedomain.entities.character.AttributesModifier
import com.angelus.gamedomain.entities.character.Skill
import com.angelus.gamedomain.entities.character.SkillType

object Unlocking : Skill {
    override val id = "unlocking"
    override val name = "unlocking"
    override val description = "Permet de crocheter les serrures."
    override val type = SkillType.TECHNICAL

    override val required: AttributeRequirement = AttributeRequirement(
        flexibility = 2
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 0,
        flexibility = 1,
        brain = 0,
        vitality = 0
    )
}