package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Rotation
import com.angelus.gamedomain.repository.PlayerRepository

data class RotatePlayerParams(var playerId: String,
                              var rotation: Rotation
)

interface RotatePlayerUseCase {
    operator fun invoke(params: RotatePlayerParams)
}

class RotatePlayerUseCaseImpl(val repository: PlayerRepository): RotatePlayerUseCase {

    override fun invoke(params: RotatePlayerParams) {
        repository.rotatePlayer(
            params.playerId,
            params.rotation
        )
    }
}