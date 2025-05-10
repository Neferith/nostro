package com.angelus.playerdomain.entities

import com.angelus.gamedomain.entities.character.Character
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.character.withItemAdded
import com.angelus.gamedomain.entities.character.withItemRemoved

data class Player(val id: String,
                  var entityPosition: EntityPosition,
    val band: PlayerBand
)

data class PlayerBand(val characters:List<Character>)


fun Player.withItemAddedToCharacter(characterId: String, itemId: String, quantity: Int = 1): Player {
    val updatedCharacters = band.characters.map { character ->
        if (character.id == characterId) {
            character.withItemAdded(itemId, quantity)
        } else {
            character
        }
    }
    return this.copy(band = band.copy(characters = updatedCharacters))
}

fun Player.withItemRemovedFromCharacter(characterId: String, itemId: String, quantity: Int = 1): Player {
    val updatedCharacters = band.characters.map { character ->
        if (character.id == characterId) {
            character.withItemRemoved(itemId, quantity)
        } else {
            character
        }
    }
    return this.copy(band = band.copy(characters = updatedCharacters))
}