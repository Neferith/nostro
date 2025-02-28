package com.angelus.mapdomain.usecase

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.mapdomain.entities.Panorama
import com.angelus.mapdomain.repository.CurrentMapRepository

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