package com.angelus.gamedomain.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Rotation

interface PlayerRepository {
    fun movePlayer(playerId: String,
                   distance: Int,
                   direction: Direction
    )
    fun rotatePlayer(playerId: String,
                     direction: Rotation
    )

}