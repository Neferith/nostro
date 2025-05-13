package com.angelus.nostro.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
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
import com.angelus.nostro.page.game.GameScreenPageFactory
import com.angelus.nostro.page.inventory.InventoryScreenFactory
import com.angelus.nostro.page.newgame.NewGamePageFactory
import com.angelus.playerdata.data.PlayerDataSource
import com.angelus.playerdata.data.factory.PlayerDataSourceFactory
import com.angelus.playerdomain.factory.PlayerUseCaseFactory
import com.angelus.playerdomain.repository.PlayerRepository


val Context.dataStore1: DataStore<Preferences> by preferencesDataStore("player_data_store_1")
 val Context.dataStore2: DataStore<Preferences> by preferencesDataStore("player_data_store_2")
 val Context.dataStore3: DataStore<Preferences> by preferencesDataStore("player_data_store_3")
 val Context.dataStore4: DataStore<Preferences> by preferencesDataStore("player_data_store_4")



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

    val gameMapDataSource: GameMapDataSource by lazy {
        val datastore = when (gameSlot) {
            1 -> makeDataStore1()
            2 -> makeDataStore2()
            3 -> makeDataStore3()
            4 -> makeDataStore4()
            else -> throw IllegalArgumentException("Invalid game slot: $gameSlot")
        }
        GameMapDataStore(ModuleAContainer().getMaps(), datastore)
    }

    override val currentMapRepository: CurrentMapRepository by lazy {
        //BaseMapRepository(ModuleAContainer().getMaps())
        SaveMapRepository(gameMapDataSource)
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
