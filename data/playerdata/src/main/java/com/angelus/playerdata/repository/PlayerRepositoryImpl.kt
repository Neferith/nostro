package com.angelus.playerdata.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Orientation
import com.angelus.playerdomain.entities.Player
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.Rotation
import com.angelus.playerdata.data.PlayerDataSource
import com.angelus.playerdomain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update

class PlayerRepositoryImpl(private val dataSource: PlayerDataSource) :
    PlayerRepository {

    // Création d'un Player par défaut
    val defaultPlayer = Player(
        id = "",
        entityPosition = EntityPosition(
            Position(1, 4),
            Orientation.NORTH
        )
    ) // Adapte les valeurs en fonction de ton modèle

    // Initialisation du StateFlow avec un player par défaut
    private val _playersState = MutableStateFlow<Map<String, Player>>(
        mapOf("" to defaultPlayer) // La carte contient le joueur par défaut
    )

    override fun observePlayer(playerId: String): Flow<Player> =
        _playersState.mapNotNull { it[playerId] }
            .distinctUntilChanged()

    override suspend fun movePlayer(
        playerId: String,
        distance: Int,
        direction: Direction
    ): Player {
        val player = getOrFetchPlayer(playerId)
        val updatedPlayer = player.copy(
            entityPosition = player.entityPosition.changePosition(distance, direction)
        )
        updatePlayer(updatedPlayer)
        return updatedPlayer
    }

    override suspend fun rotatePlayer(
        playerId: String,
        direction: Rotation
    ): Player {
        val player = getOrFetchPlayer(playerId)
        val updatedPlayer = player.copy(
            entityPosition = player.entityPosition.copy(
                orientation = when (direction) {
                    Rotation.LEFT -> player.entityPosition.orientation.rotateLeft()
                    Rotation.RIGHT -> player.entityPosition.orientation.rotateRight()
                }
            )
        )
        updatePlayer(updatedPlayer)
        return updatedPlayer
    }

    private suspend fun getOrFetchPlayer(playerId: String): Player {
        return _playersState.value[playerId] ?: dataSource.fetchPlayer(playerId).also {
            updatePlayer(it)
        }
    }

    private suspend fun updatePlayer(player: Player) {
        dataSource.updatePlayer(player)
        _playersState.update { it + (player.id to player) }
    }
}