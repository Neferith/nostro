package com.angelus.playerdomain.entities

import com.angelus.gamedomain.entities.character.Character
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.character.withItemAdded
import com.angelus.gamedomain.entities.character.withItemRemoved

class CharacterNotFound: Exception()

data class Player(val id: String,
                  var entityPosition: EntityPosition,
    val band: PlayerBand
)

data class PlayerBand(val characters:List<Character>)


fun Player.withItemAddedToCharacter(characterId: String, itemId: String, quantity: Int = 1): Player {
    var found = false
    val updatedCharacters = band.characters.map { character ->
        if (character.id == characterId) {
            found = true
            character.withItemAdded(itemId, quantity)
        } else {
            character
        }
    }

    require(found) { "Character with id '$characterId' not found in player's band." }

    return this.copy(band = band.copy(characters = updatedCharacters))
}

fun Player.withItemRemovedFromCharacter(characterId: String, itemId: String, quantity: Int = 1): Player {
    var found = false
    val updatedCharacters = band.characters.map { character ->
        if (character.id == characterId) {
            found = true
            character.withItemRemoved(itemId, quantity)
        } else {
            character
        }
    }
    require(found) { "Character with id '$characterId' not found in player's band." }
    return this.copy(band = band.copy(characters = updatedCharacters))
}