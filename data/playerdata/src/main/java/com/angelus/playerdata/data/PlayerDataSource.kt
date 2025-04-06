package com.angelus.playerdata.data

import PlayerDTO
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
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


class PlayerDataStore(private val dataStore: DataStore<Preferences>) : PlayerDataSource {

    private val playerKey = stringPreferencesKey("player")

     override suspend fun fetchPlayer(): Player? {
        val preferences = dataStore.data.first()
        val playerJson = preferences[playerKey]
        return if (playerJson != null) {
            Json.decodeFromString<PlayerDTO>(playerJson).convertFromDTO()
        } else {
            null
        }
    }

    override suspend fun updatePlayer(player: Player) {
        dataStore.edit { preferences ->
            preferences[playerKey] = Json.encodeToString(player.convertToDTO())
        }
    }
}