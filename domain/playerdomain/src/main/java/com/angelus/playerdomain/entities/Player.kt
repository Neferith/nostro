package com.angelus.playerdomain.entities

import com.angelus.gamedomain.entities.character.Character
import com.angelus.gamedomain.entities.EntityPosition

data class Player(val id: String,
                  var entityPosition: EntityPosition,
    val band: PlayerBand
)

data class PlayerBand(val characters:List<Character>)
