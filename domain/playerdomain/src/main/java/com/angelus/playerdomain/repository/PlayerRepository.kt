package com.angelus.playerdomain.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Rotation
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun observePlayer(playerId: String): Flow<com.angelus.playerdomain.entities.Player>
    suspend fun movePlayer(playerId: String,
                           distance: Int,
                           direction: Direction
    ): com.angelus.playerdomain.entities.Player
    suspend fun rotatePlayer(playerId: String,
                     direction: Rotation
    ): com.angelus.playerdomain.entities.Player

}