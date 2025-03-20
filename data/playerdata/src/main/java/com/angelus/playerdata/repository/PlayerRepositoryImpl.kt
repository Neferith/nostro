package com.angelus.playerdata.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Orientation
import com.angelus.playerdomain.entities.Player
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.Rotation
import com.angelus.playerdata.data.PlayerDataSource
import com.angelus.playerdomain.entities.PlayerBand
import com.angelus.playerdomain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update

class PlayerNotFoundException: Exception()

class PlayerRepositoryImpl(private val dataSource: PlayerDataSource) :
    PlayerRepository {

    // Création d'un Player par défaut
    val defaultPlayer = Player(
        id = "",
        entityPosition = EntityPosition(
            "",
            Position(1, 4),
            Orientation.NORTH
        ),
        PlayerBand(emptyList())
    ) // Adapte les valeurs en fonction de ton modèle

    // Initialisation du StateFlow avec un player par défaut
    private val _playersState = MutableStateFlow<Player>(
        defaultPlayer // La carte contient le joueur par défaut
    )

    override fun observePlayer(): Flow<Player> =
        _playersState.mapNotNull { it }
            .distinctUntilChanged()

    override suspend fun movePlayer(
        distance: Int,
        direction: Direction
    ): Result<Player> {
        val player = getOrFetchPlayer()
        if(player != null) {
            val updatedPlayer = player.copy(
                entityPosition = player.entityPosition.changePosition(distance, direction)
            )
            updatePlayer(updatedPlayer)
            return Result.success(updatedPlayer)
        }
        return Result.failure(PlayerNotFoundException())
    }


    override suspend fun rotatePlayer(
        direction: Rotation
    ): Result<Player> {
        val player = getOrFetchPlayer()
        if(player != null) {
            val updatedPlayer = player.copy(
                entityPosition = player.entityPosition.copy(
                    orientation = when (direction) {
                        Rotation.LEFT -> player.entityPosition.orientation.rotateLeft()
                        Rotation.RIGHT -> player.entityPosition.orientation.rotateRight()
                    }
                )
            )
            updatePlayer(updatedPlayer)
            return Result.success(updatedPlayer)
        }
        return Result.failure(PlayerNotFoundException())
    }

    override suspend fun initializePlayer(player: Player): Result<Player> {
       updatePlayer(player)
        return Result.success(player)
    }

    private suspend fun getOrFetchPlayer(): Player? {
        return _playersState.value ?: dataSource.fetchPlayer().also {
            if(it!= null) {
            updatePlayer(it)
                }
        }
    }

    private suspend fun updatePlayer(player: Player) {
        dataSource.updatePlayer(player)
        _playersState.update { player }
    }

}