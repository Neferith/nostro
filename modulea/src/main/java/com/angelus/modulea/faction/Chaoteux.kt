package com.angelus.modulea.faction

import com.angelus.gamedomain.entities.character.Character

object Chaoteux: FactionProvider {
    override val id: String = "chaoteux"
    override val name: String = "Chaoteus"
    override val description: String = "DESCRIPTION"
    override val relations: Map<String, Int> = mapOf( Character.PLAYER_FACTION to 50)

}
