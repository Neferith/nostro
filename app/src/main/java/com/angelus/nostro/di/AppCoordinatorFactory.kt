package com.angelus.nostro.di

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.gamedomain.factory.TurnUseCaseFactory
import com.angelus.mapdomain.factory.CurrentMapUseCaseFactory
import com.angelus.nostro.di.domain.GameDomainDIContainer
import com.angelus.nostro.page.game.GameScreenNavigator
import com.angelus.nostro.page.game.GameScreenPageFactory
import com.angelus.nostro.page.game.GameScreenViewModel
import com.angelus.nostro.page.menu.MenuPageFactory
import com.angelus.nostro.page.newgame.NewGamePageFactory
import com.angelus.playerdomain.factory.PlayerUseCaseFactory

class AppCoordinatorFactory(val context: Context): MenuPageFactory, NewGamePageFactory {


    @Composable
    fun MakeGameScreenPage(defaultName: String,
                           navigator: GameScreenNavigator,
                           navBackStackEntry: NavBackStackEntry
    ) {
        val container = GameDIContainer(context,
            GameParams(defaultName),
            playerUseCaseFactory = playerUseCaseFactory
        )
        container.MakeGameScreenPage(GameScreenViewModel.Params(""), navigator, navBackStackEntry)
    }

    private val domainDIContainer: NewGameDIContainer by lazy {NewGameDIContainer(context)}

    override val currentGameUseCaseFactory: CurrentGameUseCaseFactory = domainDIContainer
    override val playerUseCaseFactory: PlayerUseCaseFactory = domainDIContainer
}