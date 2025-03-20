package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequirement
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

object Bow: Skill {
    override val id = "Bow"
    override val name = "distanceWeapon"
    override val description = "Arme à distance"
    override val type = SkillType.DISTANCE

    override val required: AttributeRequirement = AttributeRequirement(
        musculature = 1,
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 1,
        flexibility = 0,
        brain = 0,
        vitality = 0
    )
}