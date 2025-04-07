package com.angelus.modulea.background.primary

import com.angelus.gamedomain.entities.character.AttributesModifier
import com.angelus.gamedomain.entities.character.Background
import com.angelus.gamedomain.entities.character.Skill
import com.angelus.modulea.skill.CommonLanguage
import com.angelus.modulea.skill.ImprovisedWeapon
import com.angelus.modulea.skill.Persuasion
import com.angelus.modulea.skill.Survival

object Farmer: Background {

    override val id: String
        get() = "farmer"
    override val description: String
        get() = "Origine simple, travailleur et proche de la terre."
    override val attributesModifier: AttributesModifier
        get() = AttributesModifier(
            musculature = +2,
            flexibility = 0,
            brain =  -3,
            vitality =  +2

        )
    override val startingSkills: List<Skill>
        get() = listOf(
            Persuasion,
            CommonLanguage,
            ImprovisedWeapon,
            Survival
        )
    override val requirement = null
}