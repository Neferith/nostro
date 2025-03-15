package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequierment
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

object ImprovisedWeapon: Skill {
    override val id = "improvisedWeapon"
    override val name = "improvisedWeapon"
    override val description = "Arme improvisées"
    override val type = SkillType.WEAPON

    override val required: AttributeRequierment = AttributeRequierment(

    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 0,
        flexibility = 1,
        brain = 0,
        vitality = 0
    )
}