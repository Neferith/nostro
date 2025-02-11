package com.angelus.nostro.coordinator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.angelus.nostro.MainNavigator
import com.angelus.nostro.MainScreen
import com.angelus.nostro.page.game.GameScreen
import com.angelus.nostro.page.game.GameScreenViewModel


// Screen.kt
sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object Game : Screen("game_screen")
}

@Composable
fun AppNavigation(appCoordinator: AppCoordinator, navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            MainScreen(appCoordinator)
        }
        composable(
            route = Screen.Game.route + "?text={text}",
            arguments = listOf(
                navArgument("text") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            GameScreen(appCoordinator, GameScreenViewModel())
        }
    }
}

class AppCoordinator(private val navController: NavHostController) : MainNavigator {
    //val navController = rememberNavController()

    @Composable
    fun Start() {
        AppNavigation(this, navController)
    }

    override fun goToDetail(value: String) {
        navController.navigate(route = Screen.Game.route )
    }
}

