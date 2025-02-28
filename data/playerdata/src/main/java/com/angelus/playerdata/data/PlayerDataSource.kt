package com.angelus.playerdata.data

import com.angelus.gamedomain.entities.Orientation
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Position
import com.angelus.playerdomain.entities.Player

interface PlayerDataSource {
    suspend fun fetchPlayer(playerId: String): Player
    suspend fun updatePlayer(player: Player)
}

class PlayerDataSourceImpl : PlayerDataSource {

    var player: Player = Player(
        "",
        EntityPosition(
            Position(
                0,
                0
            ),
            Orientation.NORTH
        )
    )


    override suspend fun fetchPlayer(playerId: String): Player {
        return player
    }

    override suspend fun updatePlayer(player: Player) {
        this.player = player
    }

}