package com.angelus.npc.domain.repository

import com.angelus.gamedomain.entities.Position
import com.angelus.npc.domain.entities.NPC
import com.angelus.npc.domain.entities.Turn
import com.angelus.npc.domain.entities.TurnType
import kotlinx.coroutines.flow.Flow

interface TurnRepository {
    fun observeTurn(): Flow<Turn>
    suspend fun nextTurn()


    suspend fun fetchVisibleNCP(positions: List<Position>): List<TurnType.NPC>

    suspend fun fetchNPC(characterId: String): Result<NPC>
    suspend fun updateNPC(npc: NPC): Result<NPC>
}