package com.angelus.nostro.di

import com.angelus.data.gamedata.data.PlayerDataSource
import com.angelus.data.gamedata.data.PlayerDataSourceImpl
import com.angelus.data.gamedata.repository.PlayerRepositoryImpl
import com.angelus.gamedomain.factory.PlayerUseCaseFactory
import com.angelus.gamedomain.repository.PlayerRepository

class DomainDIContainer() : PlayerUseCaseFactory {
    val playerDataSource: PlayerDataSource by lazy {
        PlayerDataSourceImpl()
    }

    override val playerRepository: PlayerRepository by lazy {
        PlayerRepositoryImpl(playerDataSource)
    }
}