package com.angelus.modulea.background.primary

import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Background
import com.angelus.gamedomain.entities.Skill
import com.angelus.modulea.skill.CommonLanguage
import com.angelus.modulea.skill.ImprovisedWeapon
import com.angelus.modulea.skill.Survival
import com.angelus.modulea.skill.Unlocking

object Slums : Background {
    override val id: String
        get() = "slums"
    override val description: String
        get() = "Né dans la misère, la loi du plus fort t’a forgé."
    override val attributesModifier: AttributesModifier
        get() = AttributesModifier(
            musculature = +1,
            flexibility = +1,
            brain =  -4,
            vitality =  +3

        )
    override val startingSkills: List<Skill>
        get() = listOf(
            CommonLanguage,
            ImprovisedWeapon,
            Unlocking,
            Survival
        )

    override val requirement = null
}