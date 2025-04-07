package com.angelus.gamedomain.entities

import com.angelus.gamedomain.entities.character.BackgroundType
import com.angelus.gamedomain.entities.character.Skill

interface Module {
    val skillsList: List<Skill>
    val backgrounds: List<BackgroundType>

    val startPosition: EntityPosition

    val mapList: List<String>

}

