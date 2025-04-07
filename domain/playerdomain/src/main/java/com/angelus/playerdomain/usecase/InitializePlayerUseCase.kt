package com.angelus.playerdomain.usecase

import com.angelus.gamedomain.entities.character.Character
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.entities.PlayerBand
import com.angelus.playerdomain.repository.PlayerRepository

data class InitializePlayerParams(
    val startPosition: EntityPosition,
    val character: Character
)

interface InitializePlayerUseCase {
    suspend operator fun invoke(params: InitializePlayerParams): Result<Player>
}

class InitializePlayerUseCaseImpl(val repository: PlayerRepository): InitializePlayerUseCase {
    override suspend fun invoke(params: InitializePlayerParams): Result<Player> {
       val player = Player(
           id = "",
           entityPosition = params.startPosition.copy(),
           band = PlayerBand(
               listOf(
                   params.character
               )
           )
       )
        return repository.initializePlayer(player)
    }
}