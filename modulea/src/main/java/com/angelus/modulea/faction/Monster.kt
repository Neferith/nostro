package com.angelus.modulea.faction

import com.angelus.gamedomain.entities.character.Character

object Monster: FactionProvider {
    override val id: String = "Monster"
    override val name: String = "Monster"
    override val description: String = "DESCRIPTION"
    override val relations: Map<String, Int> = mapOf( Character.PLAYER_FACTION to 0)

}
