package com.angelus.playerdata.data

import PlayerDTO
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.angelus.playerdata.data.mapper.convertFromDTO
import com.angelus.playerdata.data.mapper.convertToDTO
import com.angelus.playerdomain.entities.Player
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface PlayerDataSource {
    suspend fun fetchPlayer(): Player?
    suspend fun updatePlayer(player: Player)
}

//@TODO : INJECT DATA STORE
private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore("player_data_store")

class PlayerDataStore(private val context: Context, private val gameName: String) : PlayerDataSource {

    private val playerKey = stringPreferencesKey("player_$gameName")

     override suspend fun fetchPlayer(): Player? {
        val preferences = context.dataStore.data.first()
        val playerJson = preferences[playerKey]
        return if (playerJson != null) {
            Json.decodeFromString<PlayerDTO>(playerJson).convertFromDTO()
        } else {
            null
        }
    }

    override suspend fun updatePlayer(player: Player) {
        context.dataStore.edit { preferences ->
            preferences[playerKey] = Json.encodeToString(player.convertToDTO())
        }
    }
}