package com.angelus.playerdata.data

import PlayerDTO
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.angelus.playerdata.data.exception.PlayerNotFoundException
import com.angelus.playerdata.data.mapper.convertFromDTO
import com.angelus.playerdata.data.mapper.convertToDTO
import com.angelus.playerdomain.entities.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// Interface de DataSource
interface PlayerDataSource {
    fun observePlayer(): Flow<Result<Player>>
    suspend fun fetchPlayer(): Result<Player>
    suspend fun updatePlayer(player: Player): Result<Unit>
}

// Implémentation avec DataStore
class PlayerDataStore(private val dataStore: DataStore<Preferences>) : PlayerDataSource {

    private val playerKey = stringPreferencesKey("player")
    private val tag: String = "PlayerDataStore"

    override fun observePlayer(): Flow<Result<Player>> {
        return dataStore.data
            .map { preferences ->
                preferences[playerKey]?.let { json ->
                    Json.decodeFromString<PlayerDTO>(json).convertFromDTO()
                } ?: throw PlayerNotFoundException()
            }
            .map { Result.success(it) }
            .catch { e -> emit(Result.failure(e)) }
    }

    override suspend fun fetchPlayer(): Result<Player> {
        return try {
            val preferences = dataStore.data.first()
            val playerJson = preferences[playerKey]
                ?: return Result.failure(PlayerNotFoundException())
            Result.success(Json.decodeFromString<PlayerDTO>(playerJson).convertFromDTO())
        } catch (e: Exception) {
            Log.e(tag, "Erreur lors de la récupération du joueur", e)
            Result.failure(e)
        }
    }

    override suspend fun updatePlayer(player: Player): Result<Unit> {
        return try {
            val json = Json.encodeToString(player.convertToDTO())
            dataStore.edit { preferences ->
                preferences[playerKey] = json
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(tag, "Erreur lors de l'observation du joueur", e)
            Result.failure(e)
        }
    }
}