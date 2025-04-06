package com.angelus.playerdata.data.factory

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.angelus.playerdata.data.PlayerDataSource
import androidx.datastore.preferences.preferencesDataStore
import com.angelus.playerdata.data.PlayerDataStore

private val Context.dataStore1: DataStore<Preferences> by preferencesDataStore("player_data_store_1")
private val Context.dataStore2: DataStore<Preferences> by preferencesDataStore("player_data_store_2")
private val Context.dataStore3: DataStore<Preferences> by preferencesDataStore("player_data_store_3")
private val Context.dataStore4: DataStore<Preferences> by preferencesDataStore("player_data_store_4")

interface PlayerDataSourceFactory {

    fun makeGame1(context: Context): PlayerDataSource {
       return PlayerDataStore(context.dataStore1)
    }

    fun makeGame2(context: Context): PlayerDataSource {
        return PlayerDataStore(context.dataStore2)
    }

    fun makeGame3(context: Context): PlayerDataSource {
        return PlayerDataStore(context.dataStore3)
    }

    fun makeGame4(context: Context): PlayerDataSource {
        return PlayerDataStore(context.dataStore4)
    }
}