package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Panorama
import com.angelus.gamedomain.repository.CurrentMapRepository

interface FetchPanoramaUseCase {
    suspend operator fun invoke(entityPosition: EntityPosition): Panorama
}
class FetchPanoramaUseCaseImpl(
    private val currentMapRepository: CurrentMapRepository
): FetchPanoramaUseCase {
    override suspend fun invoke(entityPosition: EntityPosition): Panorama {
        return currentMapRepository.fetchPanoram(entityPosition)
    }
}