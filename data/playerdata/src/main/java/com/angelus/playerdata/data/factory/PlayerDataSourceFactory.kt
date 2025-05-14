package com.angelus.playerdata.data.factory

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.angelus.playerdata.data.PlayerDataSource
import com.angelus.playerdata.data.PlayerDataStore


interface PlayerDataSourceFactory {

    fun makePlayerDatasource(datastore: DataStore<Preferences>): PlayerDataSource {
        return PlayerDataStore(datastore)
    }

}