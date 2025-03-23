package com.angelus.nostro.di

import com.angelus.gamedata.repository.CurrentModuleRepositoryImpl
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.gamedomain.repository.CurrentModuleRepository
import com.angelus.nostro.di.domain.ModuleAContainer
import com.angelus.playerdomain.factory.PlayerUseCaseFactory
import com.angelus.playerdomain.repository.PlayerRepository

class NewGameDIContainer: CurrentGameUseCaseFactory, PlayerUseCaseFactory{

    override val currentModuleRepository: CurrentModuleRepository = CurrentModuleRepositoryImpl(ModuleAContainer().getModule())

    private val playerDataSource: com.angelus.playerdata.data.PlayerDataSource by lazy {
        com.angelus.playerdata.data.PlayerDataSourceImpl()
    }

    override val playerRepository: PlayerRepository by lazy {
        com.angelus.playerdata.repository.PlayerRepositoryImpl(playerDataSource)
    }


}
