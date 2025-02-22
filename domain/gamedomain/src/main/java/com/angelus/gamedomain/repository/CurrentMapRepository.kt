package com.angelus.gamedomain.repository

import com.angelus.gamedomain.entities.GameMap
import com.angelus.gamedomain.entities.Player
import kotlinx.coroutines.flow.Flow

interface CurrentMapRepository {
    suspend fun loadCurrentMap(id: String): GameMap
    fun observeCurrentMap(): Flow<GameMap>
}