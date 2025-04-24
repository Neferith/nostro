package com.angelus.mapdomain.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.mapdomain.entities.GameMap
import com.angelus.mapdomain.entities.Panorama
import com.angelus.mapdomain.entities.Tile
import kotlinx.coroutines.flow.Flow

sealed class MoveType {
    object blocked: MoveType()
    object walkable: MoveType()
    data class transition(val position: EntityPosition): MoveType()
}

interface CurrentMapRepository {
    suspend fun loadCurrentMap(id: String): GameMap
    fun getPanorama(entityPosition: EntityPosition): Panorama?
    fun observeCurrentMap(): Flow<GameMap>
    fun checkMoveInMap(
        entityPosition: EntityPosition,
        moveDistance: Int,
        direction: Direction
    ): MoveType

    fun getTileAtPosition(entityPosition: EntityPosition): Result<Tile>
}