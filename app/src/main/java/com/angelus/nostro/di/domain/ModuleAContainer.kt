package com.angelus.nostro.di.domain

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.angelus.faction.domain.entities.Faction
import com.angelus.gamedomain.entities.Module
import com.angelus.gamedomain.entities.Turn
import com.angelus.mapdomain.entities.GameMap
import com.angelus.modulea.ModuleA

val Context.dataStore1: DataStore<Preferences> by preferencesDataStore("player_data_store_1")
val Context.dataStore2: DataStore<Preferences> by preferencesDataStore("player_data_store_2")
val Context.dataStore3: DataStore<Preferences> by preferencesDataStore("player_data_store_3")
val Context.dataStore4: DataStore<Preferences> by preferencesDataStore("player_data_store_4")


class ModuleAContainer {

    private val moduleA = ModuleA

    fun getModule(): Module {
        return ModuleA
    }

    fun getMaps(): Map<String,GameMap> {
        return moduleA.getAllMaps()
    }

    fun getAllTurns(): List<Turn> {
        return moduleA.getAllTurns()
    }

    fun getFactions(): List<Faction> {
        return  moduleA.getFactions()
    }
}