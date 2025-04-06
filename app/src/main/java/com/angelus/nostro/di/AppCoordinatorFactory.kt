package com.angelus.nostro.di

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.angelus.nostro.coordinator.AppCoordinator
import com.angelus.nostro.coordinator.GameCoordinator
import com.angelus.nostro.page.menu.MenuPageFactory

class AppCoordinatorFactory(val context: Context): MenuPageFactory {

    @Composable
    fun MakeNewGamePage(appCoordinator: AppCoordinator,
                        backStackEntry: NavBackStackEntry,
                        slotId: Int
    ) {
        val container = NewGameDIContainer(context, slotId)
        val gameCoordinator = GameCoordinator(appCoordinator.navController, slotId)
        // @TODO: CHANGE CONTAINER HERE
        container.MakeNewGamePage(gameCoordinator, backStackEntry)
    }


    @Composable
    fun MakeGameScreenPage(
        appCoordinator: AppCoordinator,
        backStackEntry: NavBackStackEntry,
        slotId: Int
    ) {
        val container = NewGameDIContainer(context, slotId)
        val gameCoordinator = GameCoordinator(appCoordinator.navController, slotId)
        // @TODO: CHANGE CONTAINER HERE
        container.MakeGameScreenPage(gameCoordinator, backStackEntry)
    }
}