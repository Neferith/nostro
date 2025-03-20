package com.angelus.modulea

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Module
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.Skill
import com.angelus.modulea.background.primary.Origins
import com.angelus.modulea.background.secondary.Antecedent
import com.angelus.modulea.skill.Acrobaty
import com.angelus.modulea.skill.Bow
import com.angelus.modulea.skill.CommonLanguage
import com.angelus.modulea.skill.EvilEnergy
import com.angelus.modulea.skill.HeavyWeapon
import com.angelus.modulea.skill.HolyEnergy
import com.angelus.modulea.skill.ImprovisedWeapon
import com.angelus.modulea.skill.Intidimidation
import com.angelus.modulea.skill.LightWeapon
import com.angelus.modulea.skill.Orientation
import com.angelus.modulea.skill.Persuasion
import com.angelus.modulea.skill.Reading
import com.angelus.modulea.skill.Survival
import com.angelus.modulea.skill.Unlocking

class ModuleA() : Module {

    override val skillsList: List<Skill> = listOf(
        Acrobaty,
        Bow,
        CommonLanguage,
        EvilEnergy,
        HeavyWeapon,
        HolyEnergy,
        ImprovisedWeapon,
        Intidimidation,
        LightWeapon,
        Orientation,
        Persuasion,
        Reading,
        Survival,
        Unlocking
    )
    override var backgrounds = listOf(
        Origins,
        Antecedent
    )
    override val startPosition: EntityPosition
        get() = EntityPosition(
            mapId = "",
            position = Position(0,0),
            orientation = com.angelus.gamedomain.entities.Orientation.NORTH
        )
}


