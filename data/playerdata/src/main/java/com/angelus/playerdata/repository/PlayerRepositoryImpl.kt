package com.angelus.playerdata.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Rotation
import com.angelus.playerdata.data.PlayerDataSource
import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.repository.PlayerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class PlayerNotFoundException : Exception()

class PlayerRepositoryImpl(private val dataSource: PlayerDataSource) :
    PlayerRepository {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val playerMutex = Mutex()

    private val _playersState = MutableStateFlow<Player?>(
        null
    )

    override fun observePlayer(): Flow<Player> =
        _playersState.mapNotNull { it }
            .distinctUntilChanged()

    init {
        scope.launch {
            val player = getOrFetchPlayer()
            _playersState.update { player }
        }

    }

    // Ajouter une fonction pour nettoyer le scope si besoin
    fun clear() {
        scope.cancel()
    }

    override suspend fun movePlayer(
        distance: Int,
        direction: Direction
    ): Result<Player> {
        val player = getOrFetchPlayer()
        if (player != null) {
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
        if (player != null) {
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

    override suspend fun changePlayerZone(transition: EntityPosition): Result<Player> {
        val player = getOrFetchPlayer()
        if (player != null) {
            val updatedPlayer = player.copy(
                entityPosition = transition
            )
            updatePlayer(updatedPlayer)
            return Result.success(updatedPlayer)
        }
        return Result.failure(PlayerNotFoundException())
    }

    // @TODO : FIX THIS
    private suspend fun getOrFetchPlayer(): Player? = withContext(Dispatchers.IO) {
        _playersState.value ?: dataSource.fetchPlayer().also {
            if (it != null) {
                updatePlayer(it)
            }
        }
    }

    private suspend fun updatePlayer(player: Player) {
        playerMutex.withLock {
            dataSource.updatePlayer(player)
            _playersState.update { player }
        }
    }

}