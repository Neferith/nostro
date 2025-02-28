package com.angelus.mapdomain.usecase

import com.angelus.mapdomain.entities.GameMap
import com.angelus.mapdomain.repository.CurrentMapRepository


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