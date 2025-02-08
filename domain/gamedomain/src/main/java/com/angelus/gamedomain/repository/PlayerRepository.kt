package com.angelus.gamedomain.repository

import com.angelus.gamedomain.entities.Direction

interface PlayerRepository {
    fun movePlayer(playerId: String,
                   distance: Int,
                   direction: Direction
    )
    fun rotatePlayer(playerId: String,
                     direction: Direction
    )

}