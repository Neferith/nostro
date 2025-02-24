package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Panorama
import com.angelus.gamedomain.repository.CurrentMapRepository

interface GetPanoramaUseCase {
    operator fun invoke(entityPosition: EntityPosition): Panorama
}
class GetPanoramaUseCaseImpl(
    private val currentMapRepository: CurrentMapRepository
): GetPanoramaUseCase {
    override fun invoke(entityPosition: EntityPosition): Panorama {
        return currentMapRepository.getPanorama(entityPosition)
    }
}