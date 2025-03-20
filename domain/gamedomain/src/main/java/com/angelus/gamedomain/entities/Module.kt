package com.angelus.gamedomain.entities

interface Module {
    val skillsList: List<Skill>
    val backgrounds: List<BackgroundType>

    val startPosition: EntityPosition

}