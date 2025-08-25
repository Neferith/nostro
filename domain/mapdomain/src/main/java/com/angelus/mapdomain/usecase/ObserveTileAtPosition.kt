package com.angelus.mapdomain.usecase

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.repository.CurrentMapRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

interface ObserveTileAtPositionUseCase {
    operator suspend fun invoke(entityPosition: EntityPosition): Flow<Tile>
}

class ObserveTileAtPositionUseCaseImpl(val mapRepository: CurrentMapRepository): ObserveTileAtPositionUseCase {
    override suspend fun invoke(entityPosition: EntityPosition): Flow<Tile> {
        return mapRepository.observeMap(entityPosition.mapId)
            .mapNotNull { gameMap ->
                gameMap?.tiles?.get(entityPosition.position)
            }
    }

}