package com.angelus.modulea.background.secondary

import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.Background
import com.angelus.gamedomain.entities.BackgroundRequirement
import com.angelus.gamedomain.entities.CharacterSize
import com.angelus.modulea.skill.HolyEnergy
import com.angelus.modulea.skill.LightWeapon
import com.angelus.modulea.skill.Persuasion
import com.angelus.modulea.skill.Reading

object HerosChild : Background {

    override val id = "HerosChild"
    override val description =
        "On attend beaucoup de toi, à cause d’un parent célèbre pour ses exploits."
    override val attributesModifier: AttributesModifier
        get() = AttributesModifier(
            musculature = +1,
            flexibility = +2,
            brain = 0,
            vitality = 0

        )
    override val startingSkills = listOf(
        LightWeapon, Reading
    )

    override val requirement = BackgroundRequirement(
        characterSize = listOf(
            CharacterSize.DWARF,
            CharacterSize.SMALL,
            CharacterSize.MEDIUM,
            CharacterSize.TALL,
            CharacterSize.LARGE
            )
    )
}