package com.angelus.nostro.di

import android.content.Context
import com.angelus.gamedata.data.GameSlotDataStore
import com.angelus.gamedata.repository.LocalGameSlotRepository
import com.angelus.gamedomain.factory.GameSlotRepositoryUseCaseFactory
import com.angelus.gamedomain.repository.GameSlotRepository
import com.angelus.nostro.page.menu.MenuPageFactory
import com.angelus.playerdata.data.factory.PlayerDataSourceFactory
import com.angelus.playerdomain.repository.PlayerRepository

class MenuDIContainer(context: Context,):
    MenuPageFactory,
    PlayerDataSourceFactory,
    GameSlotRepositoryUseCaseFactory{


    override val gameSlotRepositoryUseCaseFactory: GameSlotRepositoryUseCaseFactory
        get() = this



    override val gameSlotRepository: GameSlotRepository by lazy {
        LocalGameSlotRepository(GameSlotDataStore(getDataStores(context)))
    }
}