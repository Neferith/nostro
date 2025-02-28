package com.angelus.playerdomain.usecase

import com.angelus.gamedomain.entities.Direction
import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.repository.PlayerRepository

data class MovePlayerParams(
    val playerId: String,
    val direction: Direction
)

interface MovePlayerUseCase {
    suspend operator fun invoke(params: MovePlayerParams): Player
}

class MovePlayerUseCaseImpl(private val repository: PlayerRepository) : MovePlayerUseCase {
    companion object {
        const val MOVE_DISTANCE = 1
    }

    override suspend fun invoke(params: MovePlayerParams): Player {
        return repository.movePlayer(
            params.playerId,
            MOVE_DISTANCE,
            params.direction
        )
    }
}