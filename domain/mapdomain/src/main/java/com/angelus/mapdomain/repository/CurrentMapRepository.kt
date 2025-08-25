package com.angelus.mapdomain.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.mapdomain.entities.GameMap
import com.angelus.mapdomain.entities.Panorama
import com.angelus.mapdomain.entities.Tile
import kotlinx.coroutines.flow.Flow

sealed class MoveType {
    object blocked : MoveType()
    object walkable : MoveType()
    data class transition(val position: EntityPosition) : MoveType()
}

interface CurrentMapRepository {
    suspend fun getPanorama(entityPosition: EntityPosition): Panorama?
    suspend fun checkMoveInMap(
        entityPosition: EntityPosition,
        moveDistance: Int,
        direction: Direction
    ): MoveType

    suspend fun getTileAtPosition(
        entityPosition: EntityPosition
    ): Result<Tile>

    suspend fun addObjectToTileUseCase(
        entityPosition: EntityPosition,
        objectId: String,
        quantity: Int
    ): Result<Tile>

    suspend fun removeObjectToTileUseCase(
        entityPosition: EntityPosition,
        objectId: String,
        quantity: Int
    ): Result<Tile>

    suspend fun fetchMapById(mapId: String): Result<GameMap>
    fun observeMap(mapId: String): Flow<GameMap?>
}