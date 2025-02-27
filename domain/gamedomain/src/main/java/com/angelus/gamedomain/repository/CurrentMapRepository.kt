package com.angelus.gamedomain.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.GameMap
import com.angelus.gamedomain.entities.Panorama
import kotlinx.coroutines.flow.Flow

interface CurrentMapRepository {
    suspend fun loadCurrentMap(id: String): GameMap
    fun getPanorama(entityPosition: EntityPosition): Panorama
    fun observeCurrentMap(): Flow<GameMap>
    fun checkMoveInMap(
        entityPosition: EntityPosition,
        moveDistance: Int,
        direction: Direction
    ): Boolean
}