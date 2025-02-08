package com.angelus.data.gamedata.data

import com.angelus.gamedomain.entities.Player

interface PlayerDataSource {
    suspend fun fetchPlayer(playerId: String): Player
    suspend fun updatePlayer(player: Player)
}