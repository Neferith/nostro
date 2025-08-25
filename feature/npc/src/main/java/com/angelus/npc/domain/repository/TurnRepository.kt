package com.angelus.npc.domain.repository

import com.angelus.gamedomain.entities.Position
import com.angelus.npc.domain.entities.Turn
import com.angelus.npc.domain.entities.TurnList
import com.angelus.npc.domain.entities.TurnType
import kotlinx.coroutines.flow.Flow

interface TurnRepository {
    fun observeTurn(): Flow<TurnList>
    suspend fun nextTurn()


    suspend fun fetchVisibleNCP(positions: List<Position>): List<TurnType.NPC>

    suspend fun fetchNPC(characterId: String): Result<TurnType.NPC>
    suspend fun updateNPC(npc: TurnType.NPC): Result<TurnType.NPC>
}