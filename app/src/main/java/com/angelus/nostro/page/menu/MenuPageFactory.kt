package com.angelus.nostro.page.menu

import androidx.compose.runtime.Composable
import com.angelus.nostro.page.game.GameScreen
import com.angelus.nostro.page.game.GameScreenNavigator
import com.angelus.nostro.page.game.GameScreenViewModel

interface MenuPageFactory {

    fun makeViewModel(): MenuViewModel {
        return MenuViewModel()
    }
    @Composable
    fun MakeMenuPage(navigator: MenuNavigator
    ) {
        val viewModel = makeViewModel()
        return MenuPage(viewModel, navigator)
    }
}