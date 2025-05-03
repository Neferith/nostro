package com.angelus.mapdomain.usecase

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.mapdomain.entities.Panorama
import com.angelus.mapdomain.repository.CurrentMapRepository

// @TODO : Rename this
interface GetPanoramaUseCase {
    operator suspend fun invoke(entityPosition: EntityPosition): Panorama?
}
class GetPanoramaUseCaseImpl(
    private val currentMapRepository: CurrentMapRepository
): GetPanoramaUseCase {
    override suspend fun invoke(entityPosition: EntityPosition): Panorama? {
        return currentMapRepository.getPanorama(entityPosition)
    }
}