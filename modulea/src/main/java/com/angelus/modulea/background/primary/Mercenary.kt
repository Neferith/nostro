package com.angelus.modulea.background.primary

import com.angelus.gamedomain.entities.character.AttributesModifier
import com.angelus.gamedomain.entities.character.Background
import com.angelus.gamedomain.entities.character.Skill
import com.angelus.modulea.skill.CommonLanguage
import com.angelus.modulea.skill.LightWeapon
import com.angelus.modulea.skill.Persuasion
import com.angelus.modulea.skill.Unlocking

object Mercenary: Background {

    override val id: String
        get() = "mercenary"
    override val description: String
        get() = "Combattant pour de l’or, sans maître ni foi."
    override val attributesModifier: AttributesModifier
        get() = AttributesModifier(
            musculature = +2,
            flexibility = 0,
            brain =  -2,
            vitality =  +2

        )
    override val startingSkills: List<Skill>
        get() = listOf(
            Persuasion,
            CommonLanguage,
            LightWeapon,
            Unlocking
        )

    override val requirement = null
}