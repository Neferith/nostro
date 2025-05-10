package com.angelus.playerdomain.usecase

import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.repository.PlayerRepository

interface RemoveObjectToPlayerUseCase  {
    operator suspend fun invoke(
        characterId: String,
        objectId: String,
        quantity: Int
    ): Result<Player>
}

class RemoveObjectToPlayerUseCaseImpl(
    private val repository: PlayerRepository
) : RemoveObjectToPlayerUseCase {
    override operator suspend fun invoke(
        characterId: String,
        objectId: String,
        quantity: Int
    ): Result<Player> {
        return repository.removeObjectToPlayer(characterId, objectId, quantity)
    }

}