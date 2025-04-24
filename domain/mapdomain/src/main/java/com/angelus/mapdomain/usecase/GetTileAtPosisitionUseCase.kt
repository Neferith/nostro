package com.angelus.mapdomain.usecase

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.mapdomain.entities.Panorama
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.repository.CurrentMapRepository

interface GetTileAtPosisitionUseCase {
    operator fun invoke(entityPosition: EntityPosition): Result<Tile>
}

class GetTileAtPosisitionUseCaseImpl(val mapRepository: CurrentMapRepository): GetTileAtPosisitionUseCase {
    override fun invoke(entityPosition: EntityPosition): Result<Tile> {
        return mapRepository.getTileAtPosition(entityPosition)
    }

}