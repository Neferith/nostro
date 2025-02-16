package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.Player
import com.angelus.gamedomain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow

interface ObservePlayerUseCase {
    operator fun invoke(playerId: String): Flow<Player>
}

class ObservePlayerUseCaseImpl(
    private val playerRepository: PlayerRepository
) : ObservePlayerUseCase {
    override fun invoke(playerId: String): Flow<Player> = playerRepository.observePlayer(playerId)
}