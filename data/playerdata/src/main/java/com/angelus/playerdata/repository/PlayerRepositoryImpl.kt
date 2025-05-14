package com.angelus.playerdata.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Rotation
import com.angelus.playerdata.data.PlayerDataSource
import com.angelus.playerdata.data.exception.PlayerNotFoundException
import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.entities.withItemAddedToCharacter
import com.angelus.playerdomain.entities.withItemRemovedFromCharacter
import com.angelus.playerdomain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class PlayerRepositoryImpl(private val dataSource: PlayerDataSource) :
    PlayerRepository {

    private val playerMutex = Mutex()

    override fun observePlayer(): Flow<Player> = dataSource.observePlayer()
        .mapNotNull { it.getOrNull() }

    override suspend fun movePlayer(
        distance: Int,
        direction: Direction
    ): Result<Player> {
        val player = dataSource.fetchPlayer().getOrNull()
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
        val player = dataSource.fetchPlayer().getOrNull()
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
        val player = dataSource.fetchPlayer().getOrNull()
        if (player != null) {
            val updatedPlayer = player.copy(
                entityPosition = transition
            )
            updatePlayer(updatedPlayer)
            return Result.success(updatedPlayer)
        }
        return Result.failure(PlayerNotFoundException())
    }

    override suspend fun removeObjectToPlayer(
        characterId: String,
        objectId: String,
        quantity: Int
    ): Result<Player> {


        val player = dataSource.fetchPlayer().getOrNull()
        if (player != null) {

            val updatedPlayer = player.withItemRemovedFromCharacter(characterId,objectId, quantity)
            updatePlayer(updatedPlayer)
            return Result.success(updatedPlayer)
        }
        return Result.failure(PlayerNotFoundException())
    }

    override suspend fun addObjectToPlayer(
        characterId: String,
        objectId: String,
        quantity: Int
    ): Result<Player> {
        val player = dataSource.fetchPlayer().getOrNull()
        if (player != null) {

            val updatedPlayer = player.withItemAddedToCharacter(characterId,objectId, quantity)
            updatePlayer(updatedPlayer)
            return Result.success(updatedPlayer)
        }
        return Result.failure(PlayerNotFoundException())
    }


    private suspend fun updatePlayer(player: Player) {
        playerMutex.withLock {
            dataSource.updatePlayer(player)
        }
    }

}