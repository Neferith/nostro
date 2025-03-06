package com.angelus.playerdomain.usecase

import com.angelus.gamedomain.entities.Rotation
import com.angelus.playerdomain.repository.PlayerRepository

data class RotatePlayerParams(var rotation: Rotation
)

interface RotatePlayerUseCase {
    suspend operator fun invoke(params: RotatePlayerParams): com.angelus.playerdomain.entities.Player
}

class RotatePlayerUseCaseImpl(val repository: PlayerRepository):
    RotatePlayerUseCase {
    override suspend fun invoke(params: RotatePlayerParams): com.angelus.playerdomain.entities.Player {
        return repository.rotatePlayer(
            params.rotation
        )
    }
}