package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.character.AttributeRequirement
import com.angelus.gamedomain.entities.character.AttributesModifier
import com.angelus.gamedomain.entities.character.Skill
import com.angelus.gamedomain.entities.character.SkillType

object ImprovisedWeapon: Skill {
    override val id = "improvisedWeapon"
    override val name = "improvisedWeapon"
    override val description = "Arme improvisées"
    override val type = SkillType.WEAPON

    override val required: AttributeRequirement = AttributeRequirement(

    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 0,
        flexibility = 1,
        brain = 0,
        vitality = 0
    )
}