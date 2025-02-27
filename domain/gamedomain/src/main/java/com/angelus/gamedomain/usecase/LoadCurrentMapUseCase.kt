package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.GameMap
import com.angelus.gamedomain.repository.CurrentMapRepository

interface LoadCurrentMapUseCase {
    suspend operator fun invoke(id: String): GameMap
}
class LoadCurrentMapUseCaseImpl(
    private val currentMapRepository: CurrentMapRepository
): LoadCurrentMapUseCase {
    override suspend operator fun invoke(id: String): GameMap {
        return currentMapRepository.loadCurrentMap(id)
    }
}