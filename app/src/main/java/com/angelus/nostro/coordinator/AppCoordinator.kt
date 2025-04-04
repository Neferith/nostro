package com.angelus.nostro.coordinator

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

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            //MainScreen(appCoordinator)
            appCoordinator.factory.MakeMenuPage(appCoordinator)
        }
        composable(route = Screen.NewGame.route) { backStackEntry ->
            //MainScreen(appCoordinator)
            appCoordinator.factory.MakeNewGamePage(appCoordinator, backStackEntry)
        }
        composable(
            route = Screen.Game.route + "?name={defaultName}",
            arguments = listOf(
                navArgument("defaultName") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val playerName = backStackEntry.arguments?.getString("defaultName") ?: "Joueur inconnu"

            appCoordinator.factory.MakeGameScreenPage("", appCoordinator, backStackEntry)
           // GameScreen(appCoordinator, GameScreenViewModel())
        }
    }
}

class AppCoordinator(private val navController: NavHostController) : MainNavigator,
    GameScreenNavigator,
    MenuNavigator,
   // NewGamePageFactory,
NewGameNavigator{
    //val navController = rememberNavController()

    val factory: AppCoordinatorFactory by lazy { AppCoordinatorFactory() }

    @Composable
    fun Start() {
        AppNavigation(this, navController)
    }

    override fun goToDetail(value: String) {
        navController.navigate(route = Screen.Game.route )
    }

    override fun startNewGame() {
        navController.navigate(route = Screen.NewGame.route)
    }

    override fun goToGame() {
        navController.navigate(route = Screen.Game.route)
    }
}

