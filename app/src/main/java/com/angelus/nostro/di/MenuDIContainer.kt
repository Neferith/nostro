package com.angelus.nostro.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.angelus.gamedata.data.GameSlotDataStore
import com.angelus.gamedata.repository.LocalGameSlotRepository
import com.angelus.gamedomain.factory.GameSlotRepositoryUseCaseFactory
import com.angelus.gamedomain.repository.GameSlotRepository
import com.angelus.nostro.page.menu.MenuPageFactory
import com.angelus.playerdata.data.factory.PlayerDataSourceFactory

class MenuDIContainer(val context: Context,):
    MenuPageFactory,
    PlayerDataSourceFactory,
    GameSlotRepositoryUseCaseFactory{


    override val gameSlotRepositoryUseCaseFactory: GameSlotRepositoryUseCaseFactory
        get() = this

    fun getDataStores(context: Context): List<DataStore<Preferences>> {
        return listOf(
            context.dataStore1,
            context.dataStore2,
            context.dataStore3,
            context.dataStore4
        )
    }


    override val gameSlotRepository: GameSlotRepository by lazy {
        LocalGameSlotRepository(GameSlotDataStore(getDataStores(context)))
    }

    override fun makeDataStore1(): DataStore<Preferences> {
        return context.dataStore1
    }

    override fun makeDataStore2(): DataStore<Preferences> {
        return context.dataStore2
    }

    override fun makeDataStore3(): DataStore<Preferences> {
        return context.dataStore3
    }

    override fun makeDataStore4(): DataStore<Preferences> {
        return context.dataStore4
    }
}