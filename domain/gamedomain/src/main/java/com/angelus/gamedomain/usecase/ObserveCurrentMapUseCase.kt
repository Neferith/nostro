package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.GameMap
import com.angelus.gamedomain.repository.CurrentMapRepository
import kotlinx.coroutines.flow.Flow

interface ObserveCurrentMapUseCase {
    operator fun invoke(playerId: String): Flow<GameMap>
}

class ObserveCurrentMapUseCaseImpl(
    private val currentMapRepository: CurrentMapRepository
) : ObserveCurrentMapUseCase {
    override fun invoke(playerId: String): Flow<GameMap> = currentMapRepository.observeCurrentMap()
}