package com.angelus.nostro.page.game

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.angelus.gamedomain.factory.TurnUseCaseFactory
import com.angelus.mapdomain.factory.CurrentMapUseCaseFactory
import com.angelus.nostro.page.game.GameScreenViewModel.GameUseCases
import com.angelus.nostro.page.game.GameScreenViewModel.MapUseCases
import com.angelus.nostro.page.game.GameScreenViewModel.Params
import com.angelus.nostro.page.game.GameScreenViewModel.PlayerUseCases
import com.angelus.nostro.page.newgame.NewGamePageFactory.NewGameViewModelFactory
import com.angelus.nostro.page.newgame.NewGameUseCases
import com.angelus.nostro.page.newgame.NewGameViewModel
import com.angelus.playerdomain.factory.PlayerUseCaseFactory

interface GameScreenPageFactory {

    val playerUseCaseFactory: PlayerUseCaseFactory
    val currentMapUseCaseFactory: CurrentMapUseCaseFactory
    val gameUseCaseFactory: TurnUseCaseFactory

    private class GameScreenViewModelFactory(
        val params: Params,
        private val gameUseCases: GameUseCases,
        private val playerUseCases: PlayerUseCases,
        private val mapUseCases: MapUseCases
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GameScreenViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return GameScreenViewModel(params, gameUseCases, playerUseCases, mapUseCases) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    @Composable
    private fun MakeViewModel(params: GameScreenViewModel.Params, navBackStackEntry: NavBackStackEntry): GameScreenViewModel {
        val gameUseCases = GameScreenViewModel.GameUseCases(
            gameUseCaseFactory.makeObserveTurnUseCase(),
            gameUseCaseFactory.makeNextTurnUseCase()
        )
        val playerUseCases = GameScreenViewModel.PlayerUseCases(
            playerUseCaseFactory.makeMovePlayerUseCase(),
            playerUseCaseFactory.makeRotatePlayerUseCase(),
            playerUseCaseFactory.makeObservePlayerUseCase(),
            playerUseCaseFactory.makeChangePlayerZoneUseCase()
        )
        val mapUseCases = GameScreenViewModel.MapUseCases(
            currentMapUseCaseFactory.makeObserveCurrentMapUseCase(),
            currentMapUseCaseFactory.makeFetchPanorameUseCase(),
            currentMapUseCaseFactory.makeCheckMoveInMapUseCase()
        )
      /*  return GameScreenViewModel(
            params = params,
            gameUseCases = gameUseCases,
            playerUseCases = playerUseCases,
            mapUseCases = mapUseCases
        )*/


        return viewModel(
            navBackStackEntry,
            factory = GameScreenViewModelFactory(params, gameUseCases, playerUseCases, mapUseCases)
        )
    }
    @Composable
    fun MakeGameScreenPage(params: GameScreenViewModel.Params,
                           navigator:GameScreenNavigator,
                           navBackStackEntry: NavBackStackEntry
    ) {
        val viewModel = MakeViewModel(params, navBackStackEntry)
        return GameScreen(navigator, viewModel)
    }
}