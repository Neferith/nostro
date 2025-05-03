package com.angelus.mapdata.save.datasource

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.angelus.mapdata.save.datasource.dto.GameMapDTO
import com.angelus.mapdata.save.datasource.mapper.convertFromDTO
import com.angelus.mapdata.save.datasource.mapper.convertToDTO
import com.angelus.mapdomain.entities.GameMap
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/*public class MapDataSource {
   fetchMap(id: String)
}*/

class MapNotFoundException(mapId:String): Exception("Map not found " + mapId)
class UnableToUpdateMapException(mapId: String): Exception("Unable to update ma " + mapId)

interface GameMapDataSource {
    suspend fun fetchMap(mapId: String): GameMap?
    suspend fun updateMap(map: GameMap)
}

class GameMapDataStore(
    private val basemaps: Map<String, GameMap>,
    private val dataStore: DataStore<Preferences>
) : GameMapDataSource {

    fun mapKey(id: String): Preferences.Key<String> {
        return stringPreferencesKey("map[$id]")
    }
    private val tag: String = "GameMapDataStore"

    override suspend fun fetchMap(mapId: String): GameMap? {
        return try {
          //  val myMapKey = mapKey +"["+ mapId+"]"
            val preferences = dataStore.data.first()
            val mapJson = preferences[mapKey(mapId)]
                ?: return basemaps[mapId]
            Json.decodeFromString<GameMapDTO>(mapJson).convertFromDTO()
        } catch (e: Exception) {
            Log.e(tag, "Erreur lors de la récupération du joueur", e)
           // return basemaps[mapId]
            return null
        }
    }

    override suspend fun updateMap(map: GameMap) {
        try {
            val json = Json.encodeToString(map.convertToDTO())
            dataStore.edit { preferences ->
                preferences[mapKey(map.id)] = json
            }
          //  Result.success(Unit)
        } catch (e: Exception) {
            throw UnableToUpdateMapException(map.id)
        }
    }

}
