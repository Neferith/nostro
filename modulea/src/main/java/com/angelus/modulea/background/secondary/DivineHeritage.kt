package com.angelus.modulea.background.secondary

import com.angelus.gamedomain.entities.character.AttributesModifier
import com.angelus.gamedomain.entities.character.Background
import com.angelus.gamedomain.entities.character.BackgroundRequirement
import com.angelus.gamedomain.entities.character.CharacterSize
import com.angelus.modulea.skill.HolyEnergy
import com.angelus.modulea.skill.Persuasion

object DivineHeritage : Background {

    override val id = "DivineHeritage"
    override val description =
        "Un sang béni coule dans tes veines, ou tu as été touché par une entité supérieure."
    override val attributesModifier: AttributesModifier
        get() = AttributesModifier(
            musculature = 0,
            flexibility = 0,
            brain = +2,
            vitality = +2

        )
    override val startingSkills = listOf(
        HolyEnergy, Persuasion
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