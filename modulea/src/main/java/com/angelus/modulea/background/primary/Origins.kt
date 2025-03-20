package com.angelus.modulea.background.primary

import com.angelus.gamedomain.entities.BackgroundType

object Origins: BackgroundType {
    override val id = "origins"
    override val name = "Origines"
    override val description = "Les origines de votre personnage"

    override val backgrounds = listOf(
        Farmer,
        Mercenary,
        Noble,
        Slums,
        Vagabond
    )
}