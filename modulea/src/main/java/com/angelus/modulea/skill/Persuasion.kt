package com.angelus.modulea.skill

import com.angelus.gamedomain.entities.AttributeRequierment
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.CharacterSkills
import com.angelus.gamedomain.entities.Skill
import com.angelus.gamedomain.entities.SkillType

object Persuasion : Skill {
    override val id = "persuasion"
    override val name = "Persuasion"
    override val description = "Permet de convaincre les PNJ"
    override val type = SkillType.SOCIAL

    override val required: AttributeRequierment = AttributeRequierment(
        brain = 2,
    )

    override var modifier: AttributesModifier = AttributesModifier(
        musculature = 0,
        flexibility = 0,
        brain = 1,
        vitality = 0
    )
}