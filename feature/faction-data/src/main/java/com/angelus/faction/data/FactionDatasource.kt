package com.angelus.faction.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.angelus.faction.data.dto.FactionDTO
import com.angelus.faction.data.mapper.convertFromDTO
import com.angelus.faction.data.mapper.convertToDTO
import com.angelus.faction.domain.entities.Faction
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UnableToUpdateFactionException(factionId: String): Exception()

public interface FactionDatasource {
    suspend fun fetchFaction(factionId:String): Faction?
    suspend fun updateFaction(faction: Faction)
}

class FactionDataStore(
    private val baseFactions: List<Faction>,
    private val dataStore: DataStore<Preferences>
) : FactionDatasource {

    fun factionKey(id: String): Preferences.Key<String> {
        return stringPreferencesKey("faction[$id]")
    }
    private val tag: String = "FactionDataStore"
    override suspend fun fetchFaction(factionId: String): Faction? {
        return try {
            //  val myMapKey = mapKey +"["+ mapId+"]"
            val preferences = dataStore.data.first()
            val mapJson = preferences[factionKey(factionId)]
                ?: return baseFactions.firstOrNull {it.id == factionId}
            Json.decodeFromString<FactionDTO>(mapJson).convertFromDTO()
        } catch (e: Exception) {
            Log.e(tag, "Erreur lors de la récupération du joueur", e)
            // return basemaps[mapId]
            return null
        }
    }

    override suspend fun updateFaction(faction: Faction) {
        try {
            val json = Json.encodeToString(faction.convertToDTO())
            dataStore.edit { preferences ->
                preferences[factionKey(faction.id)] = json
            }
            // Result.success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnableToUpdateFactionException(faction.id)
        }
    }


}



