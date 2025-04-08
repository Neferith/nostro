package com.angelus.nostro.page.menu

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.angelus.gamedomain.factory.GameSlotRepositoryUseCaseFactory
import com.angelus.gamedomain.usecase.FetchGameSlotUseCase
import com.angelus.nostro.page.game.GameScreen
import com.angelus.nostro.page.game.GameScreenNavigator
import com.angelus.nostro.page.game.GameScreenViewModel
import com.angelus.nostro.page.newgame.NewGamePageFactory.NewGameViewModelFactory
import com.angelus.nostro.page.newgame.NewGameUseCases
import com.angelus.nostro.page.newgame.NewGameViewModel

interface MenuPageFactory {

    val gameSlotRepositoryUseCaseFactory: GameSlotRepositoryUseCaseFactory

    class NewMenuViewModelFactory(
        private val fetchGameSlotUseCase: FetchGameSlotUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MenuViewModel(fetchGameSlotUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    @Composable
    private fun makeViewModel(navBackStackEntry: NavBackStackEntry): MenuViewModel {


        val fetchGameSlotUseCase = gameSlotRepositoryUseCaseFactory.makeFetchGameSlotUseCase()
        return viewModel(
            navBackStackEntry,
            factory = NewMenuViewModelFactory(fetchGameSlotUseCase)
        )
    }
    @Composable
    fun MakeMenuPage(
        navigator: MenuNavigator,
        navBackStackEntry: NavBackStackEntry
    ) {
        val viewModel = makeViewModel(navBackStackEntry)
        return MenuPage(viewModel, navigator)
    }
}