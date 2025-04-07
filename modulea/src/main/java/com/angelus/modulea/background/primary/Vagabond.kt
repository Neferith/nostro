package com.angelus.modulea.background.primary

import com.angelus.gamedomain.entities.character.AttributesModifier
import com.angelus.gamedomain.entities.character.Background
import com.angelus.gamedomain.entities.character.Skill
import com.angelus.modulea.skill.CommonLanguage
import com.angelus.modulea.skill.ImprovisedWeapon
import com.angelus.modulea.skill.Orientation
import com.angelus.modulea.skill.Unlocking

object Vagabond: Background {
    override val id: String
        get() = "vagabond"
    override val description: String
        get() = "Sans foyer ni attaches, tu as appris à survivre par toi-même."
    override val attributesModifier: AttributesModifier
        get() = AttributesModifier(
            musculature = +1,
            flexibility = +1,
            brain =  +1,
            vitality =  +2

        )
    override val startingSkills: List<Skill>
        get() = listOf(
            CommonLanguage,
            ImprovisedWeapon,
            Unlocking,
            Orientation
        )

    override val requirement = null
}


// Salle d'entrainement
// Librairie
// Nature
// Dans la rue