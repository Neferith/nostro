package com.angelus.data.gamedata.repository

import com.angelus.data.gamedata.data.PlayerDataSource
import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Player
import com.angelus.gamedomain.entities.Rotation
import com.angelus.gamedomain.repository.PlayerRepository

class PlayerRepositoryImpl(private val dataSource: PlayerDataSource): PlayerRepository {
    override suspend fun movePlayer(playerId: String, distance: Int, direction: Direction): Player {
        val player = dataSource.fetchPlayer(playerId)
        val updatedPlayer = player.copy(
            position = player.position.changePosition(distance, direction)
        )
        dataSource.updatePlayer(updatedPlayer)
        return updatedPlayer
    }




    override suspend fun rotatePlayer(playerId: String, direction: Rotation): Player {
        val player = dataSource.fetchPlayer(playerId)
        val updatedPlayer = player.copy(position = player.position.copy(direction = when (direction) {
            Rotation.LEFT -> player.position.direction.rotateLeft()
            Rotation.RIGHT -> player.position.direction.rotateRight()
        }))

        dataSource.updatePlayer(updatedPlayer)
        return updatedPlayer
    }
}