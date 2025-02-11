package com.angelus.nostro.coordinator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.angelus.nostro.DetailScreen
import com.angelus.nostro.MainNavigator
import com.angelus.nostro.MainScreen


// Screen.kt
sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object Detail : Screen("detail_screen")
}

@Composable
fun AppNavigation(appCoordinator: AppCoordinator, navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            MainScreen(appCoordinator)
        }
        composable(
            route = Screen.Detail.route + "?text={text}",
            arguments = listOf(
                navArgument("text") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            DetailScreen(text = it.arguments?.getString("text"))
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
        navController.navigate(route = Screen.Detail.route + "?text=${value}")
    }
}

