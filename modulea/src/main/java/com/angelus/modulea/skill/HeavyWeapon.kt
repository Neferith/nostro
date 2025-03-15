package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequierment
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

object HeavyWeapon: Skill {
    override val id = "heavyWeapon"
    override val name = "heavyWeapon"
    override val description = "Arme lourde"
    override val type = SkillType.WEAPON

    override val required: AttributeRequierment = AttributeRequierment(
        musculature = 1,
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 1,
        flexibility = 0,
        brain = 0,
        vitality = 0
    )
}