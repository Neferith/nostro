package com.angelus.nostro.di

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.gamedomain.factory.TurnUseCaseFactory
import com.angelus.mapdomain.factory.CurrentMapUseCaseFactory
import com.angelus.nostro.page.game.GameScreenNavigator
import com.angelus.nostro.page.game.GameScreenPageFactory
import com.angelus.nostro.page.game.GameScreenViewModel
import com.angelus.nostro.page.menu.MenuPageFactory
import com.angelus.nostro.page.newgame.NewGamePageFactory
import com.angelus.playerdomain.factory.PlayerUseCaseFactory

class AppCoordinatorFactory: MenuPageFactory, NewGamePageFactory {

    @Composable
    fun MakeGameScreenPage(defaultName: String,
                           navigator: GameScreenNavigator,
                           navBackStackEntry: NavBackStackEntry
    ) {
        val container = GameDIContainer(
            GameParams(defaultName),
            playerUseCaseFactory = playerUseCaseFactory
        )
        container.MakeGameScreenPage(GameScreenViewModel.Params(""), navigator, navBackStackEntry)
    }

    override val currentGameUseCaseFactory: CurrentGameUseCaseFactory = NewGameDIContainer()
    override val playerUseCaseFactory: PlayerUseCaseFactory = NewGameDIContainer()
}