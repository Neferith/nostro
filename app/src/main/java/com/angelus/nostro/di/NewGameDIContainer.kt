package com.angelus.nostro.di

import android.content.Context
import com.angelus.gamedata.repository.CurrentModuleRepositoryImpl
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.gamedomain.repository.CurrentModuleRepository
import com.angelus.nostro.di.domain.ModuleAContainer
import com.angelus.playerdomain.factory.PlayerUseCaseFactory
import com.angelus.playerdomain.repository.PlayerRepository

class NewGameDIContainer(context: Context): CurrentGameUseCaseFactory, PlayerUseCaseFactory{

    override val currentModuleRepository: CurrentModuleRepository = CurrentModuleRepositoryImpl(ModuleAContainer().getModule())

    private val playerDataSource: com.angelus.playerdata.data.PlayerDataSource by lazy {
        com.angelus.playerdata.data.PlayerDataStore(context,"")
    }

    override val playerRepository: PlayerRepository by lazy {
        com.angelus.playerdata.repository.PlayerRepositoryImpl(playerDataSource)
    }


}
