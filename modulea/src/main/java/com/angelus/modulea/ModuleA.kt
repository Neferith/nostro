package com.angelus.modulea

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Module
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.character.Skill
import com.angelus.mapdomain.entities.GameMap
import com.angelus.modulea.background.primary.Origins
import com.angelus.modulea.background.secondary.Antecedent
import com.angelus.modulea.map.Cell
import com.angelus.modulea.map.Level1
import com.angelus.modulea.map.Level2
import com.angelus.modulea.map.Level3
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
            mapId = MapIds.CELL.toString(),
            position = Position(5,3),
            orientation = com.angelus.gamedomain.entities.Orientation.WEST
        )
    override val mapList: List<String>
        get() =  listOf(
            MapIds.LEVEL_1.toString(),
            MapIds.LEVEL_2.toString(),
            MapIds.LEVEL_3.toString(),
            MapIds.CELL.toString()
    )

    fun getAllMaps(): Map<String, GameMap> {
        return mapOf(
            Level1.mapId.toString() to Level1.gameMap,
            Level2.mapId.toString() to Level2.gameMap,
            Level3.mapId.toString() to Level3.gameMap,
            Cell.mapId.toString() to Cell.gameMap
        )
    }
}

enum class MapIds {
    LEVEL_1, LEVEL_2, LEVEL_3, CELL
}

enum class MapType {
    CELL, CAVERN
}


