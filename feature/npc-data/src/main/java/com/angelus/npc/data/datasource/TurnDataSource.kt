package com.angelus.npc.data.datasource

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.angelus.npc.data.mapper.convertFromDTO
import com.angelus.npc.data.mapper.convertToDTO
import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.entities.TurnList
import com.angelus.npc.data.dto.TurnListDTO
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UnableToUpdateTurnListException: Exception()

interface TurnDataSource {
    suspend fun fetchTurn(): TurnList?
    suspend fun updateListTurn(turns: TurnList)
}


class TurnDataStore(
    private val baseTurns: List<Turn>,
    private val dataStore: DataStore<Preferences>
) : TurnDataSource {

    fun turnKey(): Preferences.Key<String> {
        return stringPreferencesKey("turnList")
    }
    private val tag: String = "GameMapDataStore"

    override suspend fun fetchTurn(): TurnList? {
        return try {
            //  val myMapKey = mapKey +"["+ mapId+"]"
            val preferences = dataStore.data.first()
            val mapJson = preferences[turnKey()]
                ?: return TurnList(baseTurns,0)
            Json.decodeFromString<TurnListDTO>(mapJson).convertFromDTO()
        } catch (e: Exception) {
            Log.e(tag, "Erreur lors de la récupération du joueur", e)
            // return basemaps[mapId]
            return null
        }
    }

    override suspend fun updateListTurn(turns: TurnList) {
        try {
            val json = Json.encodeToString(turns.convertToDTO())
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
