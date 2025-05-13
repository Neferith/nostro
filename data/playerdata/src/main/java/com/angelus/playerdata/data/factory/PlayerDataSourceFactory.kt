package com.angelus.playerdata.data.factory

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.angelus.playerdata.data.PlayerDataSource
import com.angelus.playerdata.data.PlayerDataStore

/*
private val Context.dataStore1: DataStore<Preferences> by preferencesDataStore("player_data_store_1")
private val Context.dataStore2: DataStore<Preferences> by preferencesDataStore("player_data_store_2")
private val Context.dataStore3: DataStore<Preferences> by preferencesDataStore("player_data_store_3")
private val Context.dataStore4: DataStore<Preferences> by preferencesDataStore("player_data_store_4")
*/
// TODO: REVOIR L'INJECTION DU DATA STORE
interface PlayerDataSourceFactory {

    fun makeGame1(context: Context): PlayerDataSource {
     //  return PlayerDataStore(context.dataStore1)
        return PlayerDataStore(makeDataStore1())
    }

    fun makeGame2(context: Context): PlayerDataSource {
      //  return PlayerDataStore(context.dataStore2)
        return PlayerDataStore(makeDataStore2())
    }

    fun makeGame3(context: Context): PlayerDataSource {
       // return PlayerDataStore(context.dataStore3)
        return PlayerDataStore(makeDataStore3())
    }

    fun makeGame4(context: Context): PlayerDataSource {
       // return PlayerDataStore(context.dataStore4)
        return PlayerDataStore(makeDataStore4())
    }

    fun makeDataStore1(): DataStore<Preferences>
    fun makeDataStore2(): DataStore<Preferences>
    fun makeDataStore3(): DataStore<Preferences>
    fun makeDataStore4(): DataStore<Preferences>

  /*  fun getDataStores(context: Context): List<DataStore<Preferences>> {
        return listOf(
            context.dataStore1,
            context.dataStore2,
            context.dataStore3,
            context.dataStore4
        )
    }*/
}