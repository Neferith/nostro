package com.angelus.nostro.di

import androidx.compose.runtime.Composable
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.nostro.page.game.GameScreenNavigator
import com.angelus.nostro.page.game.GameScreenViewModel
import com.angelus.nostro.page.menu.MenuPageFactory
import com.angelus.nostro.page.newgame.NewGamePageFactory
import com.angelus.playerdomain.factory.PlayerUseCaseFactory

class AppCoordinatorFactory: MenuPageFactory, NewGamePageFactory {

    @Composable
    fun MakeGameScreenPage(defaultName: String,
                           navigator: GameScreenNavigator
    ) {
        val container = GameDIContainer(
            GameParams(defaultName),
            playerUseCaseFactory = playerUseCaseFactory
        )
        container.MakeGameScreenPage(GameScreenViewModel.Params(""), navigator)
    }

    override val currentGameUseCaseFactory: CurrentGameUseCaseFactory = NewGameDIContainer()
    override val playerUseCaseFactory: PlayerUseCaseFactory = NewGameDIContainer()
}