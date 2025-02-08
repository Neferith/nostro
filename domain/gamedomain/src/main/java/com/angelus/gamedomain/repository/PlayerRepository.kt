package com.angelus.gamedomain.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Player
import com.angelus.gamedomain.entities.Rotation

interface PlayerRepository {
    suspend fun movePlayer(playerId: String,
                   distance: Int,
                   direction: Direction
    ): Player
    suspend fun rotatePlayer(playerId: String,
                     direction: Rotation
    ): Player

}