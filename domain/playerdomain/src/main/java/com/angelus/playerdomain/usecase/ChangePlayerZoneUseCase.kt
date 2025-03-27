package com.angelus.playerdomain.usecase

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.repository.PlayerRepository

data class ChangePlayerZoneParams(
    val transition: EntityPosition
)


interface ChangePlayerZoneUseCase {
    suspend operator fun invoke(params: ChangePlayerZoneParams): Result<Player>
}

class ChangePlayerZoneUseCaseImpl(private val repository: PlayerRepository) : ChangePlayerZoneUseCase {

    override suspend fun invoke(params: ChangePlayerZoneParams): Result<Player> {
        return repository.changePlayerZone(
            params.transition
        )
    }
}