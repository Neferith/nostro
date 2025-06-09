package com.angelus.playerdomain.usecase

import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.repository.PlayerRepository

interface GetPlayerUseCase {
    suspend operator fun  invoke(): Result<Player>
}

class GetPlayerUseCaseImpl(
    private val playerRepository: PlayerRepository
) : GetPlayerUseCase {
    override suspend fun invoke(): Result<Player> = playerRepository.getPlayer()

}