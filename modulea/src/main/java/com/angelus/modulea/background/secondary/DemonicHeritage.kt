package com.angelus.modulea.background.secondary

import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Background
import com.angelus.gamedomain.entities.BackgroundRequirement
import com.angelus.gamedomain.entities.Skill
import com.angelus.modulea.skill.CommonLanguage
import com.angelus.modulea.skill.EvilEnergy
import com.angelus.modulea.skill.ImprovisedWeapon
import com.angelus.modulea.skill.Intidimidation
import com.angelus.modulea.skill.Persuasion
import com.angelus.modulea.skill.Survival

object DemonicHeritage : Background {

    override val id = "demonicHeritage"
    override val description =
        "Tu portes en toi une part sombre, une malédiction de ton sang ou un pacte ancestral."
    override val attributesModifier: AttributesModifier
        get() = AttributesModifier(
            musculature = +2,
            flexibility = +1,
            brain = 0,
            vitality = +2

        )
    override val startingSkills = listOf(
        EvilEnergy, Intidimidation
    )

    override val requirement = null
}