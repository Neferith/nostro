package com.angelus.nostro.coordinator

import androidx.navigation.NavHostController
import com.angelus.nostro.di.NewGameDIContainer
import com.angelus.nostro.page.game.GameScreenNavigator
import com.angelus.nostro.page.newgame.NewGameNavigator

class GameCoordinator(private val navController: NavHostController,
                      private val slotId: Int) : NewGameNavigator, GameScreenNavigator {
    override fun goToGame() {
        navController.navigate(route = Screen.Game.route + "?slotId=" + slotId)
    }
}