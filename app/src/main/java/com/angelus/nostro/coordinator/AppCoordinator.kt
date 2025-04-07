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


// Screen.kt
sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object NewGame : Screen("new_game")
    object Game : Screen("game_screen")
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
    }
}




class AppCoordinator(context: Context, val navController: NavHostController) : MainNavigator,
    GameScreenNavigator,
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

    override fun startNewGame() {
        navController.navigate(route = Screen.NewGame.route + "?slotId=1")
    }

    override fun goToGame() {
        navController.navigate(route = Screen.Game.route)
    }
}

