package com.angelus.modulea.background.secondary

import com.angelus.gamedomain.entities.character.BackgroundType

object Antecedent : BackgroundType {
    override val id = "Antecedent"
    override val name = "Antecedent"
    override val description = "Les antecedents de votre personnage"

    override val backgrounds = listOf(
        DemonicHeritage,
        DivineHeritage,
        HerosChild,
        SicklyChild
    )
}