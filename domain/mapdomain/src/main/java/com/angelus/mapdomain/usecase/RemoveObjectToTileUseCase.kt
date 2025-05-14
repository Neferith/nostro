package com.angelus.mapdomain.usecase

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.repository.CurrentMapRepository

interface RemoveObjectToTileUseCase {
    operator suspend fun invoke(
        entityPosition: EntityPosition,
        objectId: String,
        quantity: Int
    ): Result<Tile>
}

class RemoveObjectToTileUseCaseImpl(private val repository: CurrentMapRepository) : RemoveObjectToTileUseCase {
    override suspend fun invoke(
        entityPosition: EntityPosition,
        objectId: String,
        quantity: Int
    ): Result<Tile> {
        return repository.removeObjectToTileUseCase(entityPosition, objectId, quantity)
    }

}