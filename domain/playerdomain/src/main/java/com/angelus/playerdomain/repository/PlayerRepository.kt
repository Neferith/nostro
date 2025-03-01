package com.angelus.playerdomain.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Rotation
import com.angelus.playerdomain.entities.Player
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun observePlayer(playerId: String): Flow<Player>
    suspend fun movePlayer(playerId: String,
                           distance: Int,
                           direction: Direction
    ): Player
    suspend fun rotatePlayer(playerId: String,
                     direction: Rotation
    ): Player

}