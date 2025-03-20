package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequirement
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

object LightWeapon : Skill {
    override val id = "lightWeapon"
    override val name = "lightWeapon"
    override val description = "Arme légère"
    override val type = SkillType.KNOWNESS

    override val required: AttributeRequirement = AttributeRequirement(
        musculature = -2,
        flexibility = 0,
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 0,
        flexibility = 1,
        brain = 0,
        vitality = 0
    )
}