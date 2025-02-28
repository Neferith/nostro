package com.angelus.nostro.di

import com.angelus.data.gamedata.repository.CurrentMapRepositoryImpl
import com.angelus.gamedomain.factory.CurrentMapUseCaseFactory
import com.angelus.gamedomain.repository.CurrentMapRepository
import com.angelus.playerdomain.repository.PlayerRepository

class DomainDIContainer() : com.angelus.playerdomain.factory.PlayerUseCaseFactory, CurrentMapUseCaseFactory {
    val playerDataSource: com.angelus.playerdata.data.PlayerDataSource by lazy {
        com.angelus.playerdata.data.PlayerDataSourceImpl()
    }

    override val playerRepository: PlayerRepository by lazy {
        com.angelus.playerdata.repository.PlayerRepositoryImpl(playerDataSource)
    }

    override val currentMapRepository: CurrentMapRepository by lazy {
        CurrentMapRepositoryImpl()
    }
}