package com.angelus.playerdomain.usecase

import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow

interface ObservePlayerUseCase {
    operator fun invoke(): Flow<Player>
}

class ObservePlayerUseCaseImpl(
    private val playerRepository: PlayerRepository
) : ObservePlayerUseCase {
    override fun invoke(): Flow<Player> = playerRepository.observePlayer()
}