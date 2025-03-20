package com.angelus.nostro.page.newgame

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.playerdomain.factory.PlayerUseCaseFactory


interface NewGamePageFactory {

    val currentGameUseCaseFactory: CurrentGameUseCaseFactory
    val playerUseCaseFactory: PlayerUseCaseFactory

    class NewGameViewModelFactory(
        private val useCases: NewGameUseCases
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewGameViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewGameViewModel(useCases) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


    @Composable
    private fun MakeViewModel(navBackStackEntry: NavBackStackEntry): NewGameViewModel {
        //return NewGameViewModel(navBackStackEntry)
        val useCases = NewGameUseCases(
            getAllBackgroundStoriesUseCase = currentGameUseCaseFactory.makeGetAGetAllBackgroundStoriesUseCase(),
            getStartPositionUseCase = currentGameUseCaseFactory.makeGetStartPositionUseCase(),
            inialPlayerUseCase = playerUseCaseFactory.makeInitializePlayerUseCase()
        )
        return viewModel(
            navBackStackEntry,
            factory = NewGameViewModelFactory(useCases)
        )
    }

    @Composable
    fun MakeNewGamePage(navigator: NewGameNavigator,
                        navBackStackEntry: NavBackStackEntry
    ) {
        val viewModel = MakeViewModel(navBackStackEntry)
        return NewGamePage(viewModel, navigator)
    }
}
