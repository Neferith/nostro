package com.angelus.modulea.background.primary

import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Background
import com.angelus.gamedomain.entities.Skill
import com.angelus.modulea.skill.CommonLanguage
import com.angelus.modulea.skill.LightWeapon
import com.angelus.modulea.skill.Persuasion
import com.angelus.modulea.skill.Reading

object Noble: Background {
    override val id: String
        get() = "noble"
    override val description: String
        get() = "Issu d'une grande famille, élevé dans le luxe et les intrigues."
    override val attributesModifier: AttributesModifier
        get() = AttributesModifier(
            musculature = 0,
            flexibility = +1,
            brain =  +2,
            vitality =  -2

        )
    override val startingSkills: List<Skill>
        get() = listOf(
            Persuasion,
            Reading,
            CommonLanguage,
            LightWeapon
        )

    override val requirement = null
}