package com.angelus.nostro.di

import com.angelus.gamedata.repository.CurrentModuleRepositoryImpl
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.gamedomain.repository.CurrentModuleRepository
import com.angelus.modulea.ModuleA
import com.angelus.playerdomain.factory.PlayerUseCaseFactory
import com.angelus.playerdomain.repository.PlayerRepository

class NewGameDIContainer: CurrentGameUseCaseFactory, PlayerUseCaseFactory{

    override val currentModuleRepository: CurrentModuleRepository = CurrentModuleRepositoryImpl(ModuleA())

    private val playerDataSource: com.angelus.playerdata.data.PlayerDataSource by lazy {
        com.angelus.playerdata.data.PlayerDataSourceImpl()
    }

    override val playerRepository: PlayerRepository by lazy {
        com.angelus.playerdata.repository.PlayerRepositoryImpl(playerDataSource)
    }


}
