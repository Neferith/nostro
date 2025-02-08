package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.repository.PlayerRepository


data class MovePlayerParams(
    val playerId: String,
    val direction: Direction
)

interface MovePlayerUseCase {
    operator fun invoke(params: MovePlayerParams)
}

class MovePlayerUseCaseImpl(private val repository: PlayerRepository) : MovePlayerUseCase {
    companion object {
        const val MOVE_DISTANCE = 1
    }

    override fun invoke(params: MovePlayerParams) {
        repository.movePlayer(
            params.playerId,
            MOVE_DISTANCE,
            params.direction
        )
    }
}