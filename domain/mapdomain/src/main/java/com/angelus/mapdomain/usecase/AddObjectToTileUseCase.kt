package com.angelus.mapdomain.usecase

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.repository.CurrentMapRepository

interface AddObjectToTileUseCase {
    operator suspend fun invoke(
        entityPosition: EntityPosition,
        objectId: String,
        quantity: Int
    ): Result<Tile>
}

class AddObjectToTileUseCaseImpl(private val repository: CurrentMapRepository) : AddObjectToTileUseCase {
    override suspend fun invoke(
        entityPosition: EntityPosition,
        objectId: String,
        quantity: Int
    ): Result<Tile> {
        return repository.addObjectToTileUseCase(entityPosition, objectId, quantity)
    }

}