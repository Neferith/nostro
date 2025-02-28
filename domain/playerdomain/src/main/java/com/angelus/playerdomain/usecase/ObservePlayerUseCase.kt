package com.angelus.playerdomain.usecase

import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow

interface ObservePlayerUseCase {
    operator fun invoke(playerId: String): Flow<Player>
}

class ObservePlayerUseCaseImpl(
    private val playerRepository: PlayerRepository
) : ObservePlayerUseCase {
    override fun invoke(playerId: String): Flow<Player> = playerRepository.observePlayer(playerId)
}