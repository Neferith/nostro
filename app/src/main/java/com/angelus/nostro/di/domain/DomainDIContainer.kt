package com.angelus.nostro.di.domain

import com.angelus.gamedata.repository.TurnRepositoryImpl
import com.angelus.gamedomain.factory.TurnUseCaseFactory
import com.angelus.gamedomain.repository.TurnRepository
import com.angelus.mapdata.repository.CurrentMapRepositoryImpl
import com.angelus.mapdomain.factory.CurrentMapUseCaseFactory
import com.angelus.mapdomain.repository.CurrentMapRepository
import com.angelus.playerdomain.factory.PlayerUseCaseFactory
import com.angelus.playerdomain.repository.PlayerRepository

class DomainDIContainer : PlayerUseCaseFactory,
    CurrentMapUseCaseFactory,
    TurnUseCaseFactory {
    private val playerDataSource: com.angelus.playerdata.data.PlayerDataSource by lazy {
        com.angelus.playerdata.data.PlayerDataSourceImpl()
    }

    override val playerRepository: PlayerRepository by lazy {
        com.angelus.playerdata.repository.PlayerRepositoryImpl(playerDataSource)
    }

    override val currentMapRepository: CurrentMapRepository by lazy {
        CurrentMapRepositoryImpl(ModuleAContainer().getMaps())
    }
    override val turnRepository: TurnRepository by lazy {
        TurnRepositoryImpl()
    }


}