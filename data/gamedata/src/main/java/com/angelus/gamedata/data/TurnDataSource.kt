package com.angelus.gamedata.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.angelus.gamedata.data.dto.TurnDTO
import com.angelus.gamedata.data.mapper.convertFromDTO
import com.angelus.gamedata.data.mapper.convertToDTO
import com.angelus.gamedomain.entities.Turn
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UnableToUpdateTurnListException: Exception()

interface TurnDataSource {
    suspend fun fetchTurn(): List<Turn>
    suspend fun updateListTurn(turns: List<Turn>)
}


class TurnDataStore(
    private val baseTurns: List<Turn>,
    private val dataStore: DataStore<Preferences>
) : TurnDataSource {

    fun turnKey(): Preferences.Key<String> {
        return stringPreferencesKey("turnList")
    }
    private val tag: String = "GameMapDataStore"

    override suspend fun fetchTurn(): List<Turn> {
        return try {
            //  val myMapKey = mapKey +"["+ mapId+"]"
            val preferences = dataStore.data.first()
            val mapJson = preferences[turnKey()]
                ?: return baseTurns
            Json.decodeFromString<List<TurnDTO>>(mapJson).map { it.convertFromDTO() }
        } catch (e: Exception) {
            Log.e(tag, "Erreur lors de la récupération du joueur", e)
            // return basemaps[mapId]
            return emptyList()
        }
    }

    override suspend fun updateListTurn(turns: List<Turn>) {
        try {
            val json = Json.encodeToString(turns.map { it.convertToDTO() })
            dataStore.edit { preferences ->
                preferences[turnKey()] = json
            }
            // Result.success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnableToUpdateTurnListException()
        }
    }

}
