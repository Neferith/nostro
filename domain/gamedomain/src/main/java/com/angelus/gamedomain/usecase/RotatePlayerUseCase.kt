package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Player
import com.angelus.gamedomain.entities.Rotation
import com.angelus.gamedomain.repository.PlayerRepository

data class RotatePlayerParams(var playerId: String,
                              var rotation: Rotation
)

interface RotatePlayerUseCase {
    suspend operator fun invoke(params: RotatePlayerParams): Player
}

class RotatePlayerUseCaseImpl(val repository: PlayerRepository): RotatePlayerUseCase {
    override suspend fun invoke(params: RotatePlayerParams): Player {
        return repository.rotatePlayer(
            params.playerId,
            params.rotation
        )
    }
}