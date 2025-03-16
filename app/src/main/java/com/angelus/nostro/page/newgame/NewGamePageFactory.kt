package com.angelus.nostro.page.newgame

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.gamedomain.factory.TurnUseCaseFactory
import com.angelus.gamedomain.usecase.GetAllBackgroundStoriesUseCase


interface NewGamePageFactory {

    val currentGameUseCaseFactory: CurrentGameUseCaseFactory

    class NewGameViewModelFactory(
        private val getAllBackgroundStoriesUseCase: GetAllBackgroundStoriesUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewGameViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewGameViewModel(getAllBackgroundStoriesUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


    @Composable
    private fun MakeViewModel(navBackStackEntry: NavBackStackEntry): NewGameViewModel {
        //return NewGameViewModel(navBackStackEntry)
        return viewModel(
            navBackStackEntry,
            factory = NewGameViewModelFactory(currentGameUseCaseFactory.makeGetAGetAllBackgroundStoriesUseCase())
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
