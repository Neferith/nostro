package com.angelus.playerdata.data

import PlayerDTO
import android.content.Context
import com.angelus.gamedomain.entities.Orientation
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Position
import com.angelus.playerdomain.entities.Player

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.angelus.playerdomain.entities.PlayerBand
import convertPlayerFromDTO
import convertPlayerToDTO
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

interface PlayerDataSource {
    suspend fun fetchPlayer(): Player?
    suspend fun updatePlayer(player: Player)
}

/*class PlayerDataSourceImpl : PlayerDataSource {

    var player: Player = Player(
        "",
        EntityPosition(
            "",
            Position(
                0,
                0
            ),
            Orientation.NORTH
        ),
        PlayerBand(emptyList())
    )


    override suspend fun fetchPlayer(): Player {
        return player
    }

    override suspend fun updatePlayer(player: Player) {
        this.player = player
    }

}*/

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore("player_data_store")

class PlayerDataStore(private val context: Context, private val gameName: String) : PlayerDataSource {

    private val playerKey = stringPreferencesKey("player_$gameName")

     override suspend fun fetchPlayer(): Player? {
        val preferences = context.dataStore.data.first()
        val playerJson = preferences[playerKey]
        return if (playerJson != null) {
            Json.decodeFromString<PlayerDTO>(playerJson).convertPlayerFromDTO()
        } else {
            null
        }
    }

    override suspend fun updatePlayer(player: Player) {
        context.dataStore.edit { preferences ->
            preferences[playerKey] = Json.encodeToString(player.convertPlayerToDTO())
        }
    }
}