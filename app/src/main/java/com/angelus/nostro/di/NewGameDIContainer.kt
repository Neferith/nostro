package com.angelus.nostro.di

import android.content.Context
import com.angelus.gamedata.repository.CurrentModuleRepositoryImpl
import com.angelus.gamedata.repository.TurnRepositoryImpl
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.gamedomain.factory.TurnUseCaseFactory
import com.angelus.gamedomain.repository.CurrentModuleRepository
import com.angelus.gamedomain.repository.TurnRepository
import com.angelus.mapdata.repository.CurrentMapRepositoryImpl
import com.angelus.mapdomain.factory.CurrentMapUseCaseFactory
import com.angelus.mapdomain.repository.CurrentMapRepository
import com.angelus.nostro.di.domain.ModuleAContainer
import com.angelus.nostro.page.game.GameScreenPageFactory
import com.angelus.nostro.page.inventory.InventoryScreenFactory
import com.angelus.nostro.page.newgame.NewGamePageFactory
import com.angelus.playerdata.data.PlayerDataSource
import com.angelus.playerdata.data.factory.PlayerDataSourceFactory
import com.angelus.playerdomain.factory.PlayerUseCaseFactory
import com.angelus.playerdomain.repository.PlayerRepository


class NewGameDIContainer(
    context: Context,
    gameSlot: Int,
    moduleContainer: ModuleAContainer
):
    CurrentGameUseCaseFactory,
    PlayerUseCaseFactory,
    PlayerDataSourceFactory,
    CurrentMapUseCaseFactory,
    NewGamePageFactory,
    TurnUseCaseFactory,
    GameScreenPageFactory,
    InventoryScreenFactory {

    override val currentModuleRepository: CurrentModuleRepository = CurrentModuleRepositoryImpl(moduleContainer.getModule())

    private val playerDataSource: PlayerDataSource by lazy {
        when (gameSlot) {
            1 -> makeGame1(context)
            2 -> makeGame2(context)
            3 -> makeGame3(context)
            4 -> makeGame4(context)
            else -> throw IllegalArgumentException("Invalid game slot: $gameSlot")
        }
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


    override val currentGameUseCaseFactory: CurrentGameUseCaseFactory
        get() = this
    override val playerUseCaseFactory: PlayerUseCaseFactory
        get() = this
    override val currentMapUseCaseFactory: CurrentMapUseCaseFactory
        get() = this
    override val gameUseCaseFactory: TurnUseCaseFactory
        get() = this
}
