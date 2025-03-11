package com.angelus.nostro.page.newgame

import androidx.compose.runtime.Composable
import com.angelus.nostro.page.menu.MenuNavigator
import com.angelus.nostro.page.menu.MenuPage


interface NewGamePageFactory {
    private fun makeViewModel(): NewGameViewModel {
        return NewGameViewModel()
    }

    @Composable
    fun MakeNewGamePage(navigator: NewGameNavigator
    ) {
        val viewModel = makeViewModel()
        return NewGamePage(viewModel, navigator)
    }
}
