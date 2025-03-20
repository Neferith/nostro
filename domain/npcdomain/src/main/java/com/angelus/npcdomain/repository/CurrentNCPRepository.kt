package com.angelus.npcdomain.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Rotation
import com.angelus.npcdomain.entities.NPC
import kotlinx.coroutines.flow.Flow

interface CurrentNCPRepository {
    fun observeNPCsInMap(mapId: String): Flow<List<NPC>>
    suspend fun moveNPC(npcId: String,
                        distance: Int,
                        direction: Direction
    ): Result<NPC>
    suspend fun rotateNPC(npcID: String,
                          direction: Rotation
    ): Result<NPC>
}