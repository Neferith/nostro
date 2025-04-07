package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.character.AttributeRequirement
import com.angelus.gamedomain.entities.character.AttributesModifier
import com.angelus.gamedomain.entities.character.Skill
import com.angelus.gamedomain.entities.character.SkillType

object HeavyWeapon: Skill {
    override val id = "heavyWeapon"
    override val name = "heavyWeapon"
    override val description = "Arme lourde"
    override val type = SkillType.WEAPON

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