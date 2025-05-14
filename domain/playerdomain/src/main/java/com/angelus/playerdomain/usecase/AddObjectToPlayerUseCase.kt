package com.angelus.playerdomain.usecase

import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.repository.PlayerRepository

interface AddObjectToPlayerUseCase {
    operator suspend fun invoke(
    characterId: String,
    objectId: String,
    quantity: Int
): Result<Player>
}

class AddObjectToPlayerUseCaseImpl(private val repository: PlayerRepository) : AddObjectToPlayerUseCase {
    override operator suspend fun invoke(
        characterId: String,
        objectId: String,
        quantity: Int
    ): Result<Player> {
        return repository.addObjectToPlayer(characterId, objectId, quantity)
    }

}