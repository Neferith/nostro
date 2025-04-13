package com.angelus.nostro.coordinator

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.angelus.nostro.MainNavigator
import com.angelus.nostro.di.AppCoordinatorFactory
import com.angelus.nostro.page.game.GameScreenNavigator
import com.angelus.nostro.page.menu.MenuNavigator
import com.angelus.nostro.page.newgame.NewGameNavigator

enum class IntentoryPosition(val index: Int) {
    CHEST(index = -1), FLOOR(index = 0), PLAYER_1(1), PLAYER_2(2), PLAYER_3(3), PLAYER_4(4);

    companion object {
        fun valueOfIndex(position: Int): IntentoryPosition {
            IntentoryPosition.values().onEach {
                if(it.index == position) {
                    return it
                }
            }
            return PLAYER_1
        }
    }
}

// Screen.kt
sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object NewGame : Screen("new_game")
    object Game : Screen("game_screen")
    data object Inventory : Screen("game/{slotId}/inventory/{position}") {
        fun createRoute(slotId: Int, position:IntentoryPosition) = "game/$slotId/inventory/${position.index}"
    }

}

@Composable
fun AppNavigation(appCoordinator: AppCoordinator, navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) { backStackEntry ->
            //MainScreen(appCoordinator)
            appCoordinator.factory.MakeMenuPage(appCoordinator, backStackEntry)
        }
        composable(
            route = Screen.NewGame.route+ "?slotId={slotId}",
            arguments = listOf(
                navArgument("slotId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { backStackEntry ->

            val slotId = backStackEntry.arguments?.getInt("slotId") ?: 1
            //MainScreen(appCoordinator)
            appCoordinator.factory.MakeNewGamePage(appCoordinator, backStackEntry, slotId)
        }
        composable(
            route = Screen.Game.route + "?slotId={slotId}",
            arguments = listOf(
                navArgument("slotId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val slotId = backStackEntry.arguments?.getInt("slotId") ?: 1

           appCoordinator.factory.MakeGameScreenPage(appCoordinator, backStackEntry, slotId)

        }
        composable(
            route = Screen.Inventory.route, // Utilise la version avec {material}
            arguments = listOf(
                navArgument("slotId") { type = NavType.IntType },
                navArgument("position") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val slotId = backStackEntry.arguments?.getInt("slotId") ?: 1
            val position = backStackEntry.arguments?.getInt("position") ?: 1
            appCoordinator.factory.MakeInventoryPage(appCoordinator, backStackEntry,slotId, position)
        }
    }
}




class AppCoordinator(context: Context, val navController: NavHostController) : MainNavigator,
   // GameScreenNavigator,
    MenuNavigator,
   // NewGamePageFactory,
NewGameNavigator{
    //val navController = rememberNavController()

    val factory: AppCoordinatorFactory by lazy { AppCoordinatorFactory(context) }

    @Composable
    fun Start() {
        AppNavigation(this, navController)
    }

    override fun goToDetail(value: String) {
        navController.navigate(route = Screen.Game.route )
    }

    override fun startNewGame(slotId: Int) {
        navController.navigate(route = Screen.NewGame.route + "?slotId="+slotId)
    }

    override fun continueGame(slotId: Int) {
        navController.navigate(route = Screen.Game.route + "?slotId="+slotId)
    }

    override fun goToGame() {
        navController.navigate(route = Screen.Game.route)
    }

   /* override fun goToFloorInventory() {
        TODO("Not yet implemented")
    }*/
}

