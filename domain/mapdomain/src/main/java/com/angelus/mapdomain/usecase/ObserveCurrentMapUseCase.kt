package com.angelus.mapdomain.usecase

import com.angelus.mapdomain.entities.GameMap
import com.angelus.mapdomain.repository.CurrentMapRepository
import kotlinx.coroutines.flow.Flow

interface ObserveCurrentMapUseCase {
    operator fun invoke(playerId: String): Flow<GameMap>
}

class ObserveCurrentMapUseCaseImpl(
    private val currentMapRepository: CurrentMapRepository
) : ObserveCurrentMapUseCase {
    override fun invoke(playerId: String): Flow<GameMap> = currentMapRepository.observeCurrentMap()
}