package com.angelus.gamedomain.repository

import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.entities.TurnType
import kotlinx.coroutines.flow.Flow

interface TurnRepository {
    fun observeTurn(): Flow<Turn>
    suspend fun nextTurn()


    suspend fun fetchVisibleNCP(positions: List<Position>): List<TurnType.NPC>
}