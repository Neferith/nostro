package com.angelus.modulea.background.secondary

import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Background
import com.angelus.gamedomain.entities.BackgroundRequirement
import com.angelus.gamedomain.entities.CharacterSize
import com.angelus.modulea.skill.LightWeapon
import com.angelus.modulea.skill.Reading

object SicklyChild : Background {

    override val id = "SicklyChild"
    override val description =
        "Faible depuis toujours, tu as appris à observer et à contourner tes limites."
    override val attributesModifier: AttributesModifier
        get() = AttributesModifier(
            musculature = 0,
            flexibility = 0,
            brain = +4,
            vitality = -2

        )
    override val startingSkills = listOf(
         Reading
    )

    override val requirement = BackgroundRequirement(
        characterSize = listOf(
            CharacterSize.DWARF,
            CharacterSize.SMALL,
            CharacterSize.MEDIUM
        )
    )
}