package com.angelus.nostro.di

import android.content.Context
import com.angelus.faction.data.FactionDataStore
import com.angelus.faction.data.FactionDatasource
import com.angelus.faction.data.repository.LocalFactionRepository
import com.angelus.faction.domain.factory.FactionUseCasFactory
import com.angelus.faction.domain.repository.FactionRepository
import com.angelus.gamedata.data.TurnDataSource
import com.angelus.gamedata.data.TurnDataStore
import com.angelus.gamedata.repository.CurrentModuleRepositoryImpl
import com.angelus.gamedata.repository.TurnRepositoryImpl
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.gamedomain.factory.TurnUseCaseFactory
import com.angelus.gamedomain.repository.CurrentModuleRepository
import com.angelus.gamedomain.repository.TurnRepository
import com.angelus.mapdata.save.datasource.GameMapDataSource
import com.angelus.mapdata.save.datasource.GameMapDataStore
import com.angelus.mapdata.save.repository.SaveMapRepository
import com.angelus.mapdomain.factory.CurrentMapUseCaseFactory
import com.angelus.mapdomain.repository.CurrentMapRepository
import com.angelus.nostro.di.domain.ModuleAContainer
import com.angelus.nostro.di.domain.dataStore1
import com.angelus.nostro.di.domain.dataStore2
import com.angelus.nostro.di.domain.dataStore3
import com.angelus.nostro.di.domain.dataStore4
import com.angelus.nostro.page.game.GameScreenPageFactory
import com.angelus.nostro.page.inventory.InventoryScreenFactory
import com.angelus.nostro.page.newgame.NewGamePageFactory
import com.angelus.playerdata.data.PlayerDataSource
import com.angelus.playerdata.data.factory.PlayerDataSourceFactory
import com.angelus.playerdomain.factory.PlayerUseCaseFactory
import com.angelus.playerdomain.repository.PlayerRepository


class NewGameDIContainer(
    val context: Context,
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
    InventoryScreenFactory,
    FactionUseCasFactory {

    override val currentModuleRepository: CurrentModuleRepository = CurrentModuleRepositoryImpl(moduleContainer.getModule())

    private val playerDataSource: PlayerDataSource by lazy {
        when (gameSlot) {
            1 -> makePlayerDatasource(context.dataStore1)
            2 -> makePlayerDatasource(context.dataStore2)
            3 -> makePlayerDatasource(context.dataStore3)
            4 -> makePlayerDatasource(context.dataStore4)
            else -> throw IllegalArgumentException("Invalid game slot: $gameSlot")
        }
    }

    override val playerRepository: PlayerRepository by lazy {
        com.angelus.playerdata.repository.PlayerRepositoryImpl(playerDataSource)
    }

    val factionMapDataSource: FactionDatasource by lazy {
        val datastore = when (gameSlot) {
            1 -> context.dataStore1
            2 -> context.dataStore2
            3 -> context.dataStore3
            4 -> context.dataStore4
            else -> throw IllegalArgumentException("Invalid game slot: $gameSlot")
        }
        FactionDataStore(ModuleAContainer().getFactions(), datastore)
    }

    val gameMapDataSource: GameMapDataSource by lazy {
        val datastore = when (gameSlot) {
            1 -> context.dataStore1
            2 -> context.dataStore2
            3 -> context.dataStore3
            4 -> context.dataStore4
            else -> throw IllegalArgumentException("Invalid game slot: $gameSlot")
        }
        GameMapDataStore(ModuleAContainer().getMaps(), datastore)
    }

    val turnDataSource: TurnDataSource by lazy {
        val datastore = when (gameSlot) {
            1 -> context.dataStore1
            2 -> context.dataStore2
            3 -> context.dataStore3
            4 -> context.dataStore4
            else -> throw IllegalArgumentException("Invalid game slot: $gameSlot")
        }
        TurnDataStore(ModuleAContainer().getAllTurns(), datastore)
    }

    override val currentMapRepository: CurrentMapRepository by lazy {
        SaveMapRepository(gameMapDataSource)
    }
    override val turnRepository: TurnRepository by lazy {
        TurnRepositoryImpl(turnDataSource)
    }

    override val repository: FactionRepository by lazy {
        LocalFactionRepository(factionMapDataSource)
    }

    override val currentGameUseCaseFactory: CurrentGameUseCaseFactory
        get() = this
    override val playerUseCaseFactory: PlayerUseCaseFactory
        get() = this
    override val currentMapUseCaseFactory: CurrentMapUseCaseFactory
        get() = this
    override val gameUseCaseFactory: TurnUseCaseFactory
        get() = this
    override val factionUseCaseFactory: FactionUseCasFactory
        get() = this


}
