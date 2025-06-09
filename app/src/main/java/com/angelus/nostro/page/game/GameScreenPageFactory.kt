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
import com.angelus.nostro.page.game.GameScreenViewModel.PlayerUseCases
import com.angelus.nostro.section.turn.TurnSectionFactory
import com.angelus.playerdomain.factory.PlayerUseCaseFactory

interface GameScreenPageFactory {

    val playerUseCaseFactory: PlayerUseCaseFactory
    val currentMapUseCaseFactory: CurrentMapUseCaseFactory
    val gameUseCaseFactory: TurnUseCaseFactory

    private class GameScreenViewModelFactory(
        // val params: Params,
        private val gameUseCases: GameUseCases,
        private val playerUseCases: PlayerUseCases,
        private val mapUseCases: MapUseCases
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GameScreenViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return GameScreenViewModel(/*params, */gameUseCases,
                    playerUseCases,
                    mapUseCases
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    @Composable
    private fun MakeViewModel(
        navBackStackEntry: NavBackStackEntry
    ): GameScreenViewModel {
        val gameUseCases = GameUseCases(
            gameUseCaseFactory.makeObserveTurnUseCase(),
            gameUseCaseFactory.makeNextTurnUseCase(),
            gameUseCaseFactory.makeFetchVisibleNCPUseCase()
        )
        val playerUseCases = PlayerUseCases(
            playerUseCaseFactory.makeMovePlayerUseCase(),
            playerUseCaseFactory.makeRotatePlayerUseCase(),
            playerUseCaseFactory.makeObservePlayerUseCase(),
            playerUseCaseFactory.makeChangePlayerZoneUseCase()
        )
        val mapUseCases = MapUseCases(
            currentMapUseCaseFactory.makeFetchPanorameUseCase(),
            currentMapUseCaseFactory.makeCheckMoveInMapUseCase()
        )

        return viewModel(
            navBackStackEntry,
            factory = GameScreenViewModelFactory(
                gameUseCases,
                playerUseCases,
                mapUseCases
            )
        )
    }

    @Composable
    fun MakeGameScreenPage(
        navigator: GameScreenNavigator,
        navBackStackEntry: NavBackStackEntry
    ) {
        val viewModel = MakeViewModel(/*params,*/ navBackStackEntry)
        // TODO : Move Di container outside
        return GameScreen(navigator, viewModel, TurnSectionDIContainer(
            navBackStackEntry,
            playerUseCaseFactory,
            currentMapUseCaseFactory,
            gameUseCaseFactory
        ))
    }
}

class TurnSectionDIContainer(
    override val navBackStackEntry: NavBackStackEntry,
    override val playerUseCaseFactory: PlayerUseCaseFactory,
    override val currentMapUseCaseFactory: CurrentMapUseCaseFactory,
    override val gameUseCaseFactory: TurnUseCaseFactory
) : TurnSectionFactory {

}