package com.angelus.nostro.coordinator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.angelus.nostro.MainNavigator
import com.angelus.nostro.MainScreen
import com.angelus.nostro.di.AppCoordinatorFactory
import com.angelus.nostro.page.game.GameScreenNavigator
import com.angelus.nostro.page.game.GameScreenViewModel
import com.angelus.nostro.page.menu.MenuNavigator
import com.angelus.nostro.page.menu.MenuPage
import com.angelus.nostro.page.newgame.NewGamePageFactory


// Screen.kt
sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object Game : Screen("game_screen")
}

@Composable
fun AppNavigation(appCoordinator: AppCoordinator, navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            //MainScreen(appCoordinator)
            appCoordinator.factory.MakeMenuPage(appCoordinator)
        }
        composable(
            route = Screen.Game.route + "?name={defaultName}",
            arguments = listOf(
                navArgument("defaultName") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            val playerName = it.arguments?.getString("defaultName") ?: "Joueur inconnu"

            appCoordinator.factory.MakeGameScreenPage("", appCoordinator)
           // GameScreen(appCoordinator, GameScreenViewModel())
        }
    }
}

class AppCoordinator(private val navController: NavHostController) : MainNavigator,
    GameScreenNavigator,
    MenuNavigator,
    NewGamePageFactory {
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
        navController.navigate(route = Screen.Game.route +"?name=defaultName")
    }
}

