package com.angelus.nostro.di

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.angelus.nostro.coordinator.AppCoordinator
import com.angelus.nostro.coordinator.GameCoordinator
import com.angelus.nostro.coordinator.IntentoryPosition
import com.angelus.nostro.di.domain.ModuleAContainer
import com.angelus.nostro.page.menu.MenuPageFactory

class AppCoordinatorFactory(val context: Context)/*: MenuPageFactory*/ {
    private val moduleAContainer = ModuleAContainer()

    @Composable
    fun MakeMenuPage(appCoordinator: AppCoordinator,
                        backStackEntry: NavBackStackEntry
    ) {
        val container = MenuDIContainer(context)
      //  val gameCoordinator = GameCoordinator(appCoordinator.navController, slotId)
        // @TODO: CHANGE CONTAINER HERE
        container.MakeMenuPage(appCoordinator, backStackEntry)
    }

    @Composable
    fun MakeNewGamePage(appCoordinator: AppCoordinator,
                        backStackEntry: NavBackStackEntry,
                        slotId: Int
    ) {
        val container = NewGameDIContainer(context, slotId, moduleAContainer)
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
        val container = NewGameDIContainer(context, slotId, moduleAContainer)
        val gameCoordinator = GameCoordinator(appCoordinator.navController, slotId)
        // @TODO: CHANGE CONTAINER HERE
        container.MakeGameScreenPage(gameCoordinator, backStackEntry)
    }

    @Composable
    fun MakeInventoryPage(appCoordinator: AppCoordinator,
                          backStackEntry: NavBackStackEntry, slotId: Int, position: Int) {

        val container = NewGameDIContainer(context, slotId, moduleAContainer)
        val gameCoordinator = GameCoordinator(appCoordinator.navController, slotId)
        // @TODO: CHANGE CONTAINER HERE
        container.MakeInventoryScreenPage(
            IntentoryPosition.valueOfIndex(position),
            gameCoordinator,
            backStackEntry
        )

    }
}