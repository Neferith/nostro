package com.angelus.gamedata.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.angelus.gamedomain.entities.GameSlot
import kotlinx.coroutines.flow.first


interface GameSlotDataSource {
    suspend fun fetchGameSlot(): Result<List<GameSlot>>
}

class GameSlotDataStore(private val dataStores: List<DataStore<Preferences>>): GameSlotDataSource {
    override suspend fun fetchGameSlot(): Result<List<GameSlot>> {
        val gameslots = dataStores.mapIndexed { index, dataStore ->
            if (dataStore.containsSave()) {
                GameSlot.loadGame(index + 1) // +1 car index commence à 0
            } else {
                GameSlot.newGame(index + 1)
            }
        }
        return Result.success(gameslots)

    }

}


private suspend fun DataStore<Preferences>.containsSave(): Boolean {
    val playerKey = stringPreferencesKey("player")
    val prefs = this.data.first()
    return prefs.contains(playerKey)
}