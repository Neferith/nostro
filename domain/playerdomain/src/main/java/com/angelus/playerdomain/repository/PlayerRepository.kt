package com.angelus.playerdomain.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Rotation
import com.angelus.playerdomain.entities.Player
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun observePlayer(): Flow<Player>
    suspend fun movePlayer(distance: Int,
                           direction: Direction
    ): Result<Player>
    suspend fun rotatePlayer(
        direction: Rotation
    ): Result<Player>

    suspend fun initializePlayer(
        player: Player
    ): Result<Player>

    suspend fun changePlayerZone(transition: EntityPosition): Result<Player>
    suspend fun removeObjectToPlayer(
        characterId: String,
        objectId: String,
        quantity: Int
    ): Result<Player>
    suspend fun addObjectToPlayer(
        characterId: String,
        objectId: String,
        quantity: Int
    ): Result<Player>

}