package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequierment
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

object Unlocking : Skill {
    override val id = "unlocking"
    override val name = "unlocking"
    override val description = "Permet de crocheter les serrures."
    override val type = SkillType.TECHNICAL

    override val required: AttributeRequierment = AttributeRequierment(
        flexibility = 2
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 0,
        flexibility = 1,
        brain = 0,
        vitality = 0
    )
}