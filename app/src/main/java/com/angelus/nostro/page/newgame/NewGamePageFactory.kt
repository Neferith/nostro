package com.angelus.nostro.page.newgame

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry


interface NewGamePageFactory {
    @Composable
    private fun MakeViewModel(navBackStackEntry: NavBackStackEntry): NewGameViewModel {
        //return NewGameViewModel(navBackStackEntry)
        return viewModel<NewGameViewModel>(navBackStackEntry)
    }

    @Composable
    fun MakeNewGamePage(navigator: NewGameNavigator,
                        navBackStackEntry: NavBackStackEntry
    ) {
        val viewModel = MakeViewModel(navBackStackEntry)
        return NewGamePage(viewModel, navigator)
    }
}
