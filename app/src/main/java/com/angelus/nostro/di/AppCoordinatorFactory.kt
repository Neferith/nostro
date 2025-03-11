package com.angelus.nostro.di

import androidx.compose.runtime.Composable
import com.angelus.gamedomain.factory.TurnUseCaseFactory
import com.angelus.nostro.page.game.GameScreen
import com.angelus.nostro.page.game.GameScreenNavigator
import com.angelus.nostro.page.game.GameScreenPageFactory
import com.angelus.nostro.page.game.GameScreenViewModel
import com.angelus.nostro.page.menu.MenuPageFactory
import com.angelus.nostro.page.newgame.NewGamePageFactory

class AppCoordinatorFactory(): MenuPageFactory, NewGamePageFactory /*: GameScreenPageFactory */{
    // Initialise le DomainDIContainer de manière lazy (lorsqu'il est utilisé pour la première fois)
  /*  private val domainDIContainer: DomainDIContainer by lazy {
        DomainDIContainer() // Exemple avec PlayerRepositoryImpl et PlayerDataSource
    }

    // Accès aux use cases via domainDIContainer
    override val playerUseCaseFactory get() = domainDIContainer
    override val currentMapUseCaseFactory get() = domainDIContainer
    override val gameUseCaseFactory get() = domainDIContainer*/

    @Composable
    fun MakeGameScreenPage(defaultName: String,
                           navigator: GameScreenNavigator
    ) {
        val container = GameDIContainer(GameParams(defaultName))
        container.MakeGameScreenPage(GameScreenViewModel.Params(""), navigator)
    }
}