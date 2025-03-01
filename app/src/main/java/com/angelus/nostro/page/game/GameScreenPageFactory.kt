package com.angelus.nostro.page.game

import androidx.compose.runtime.Composable
import com.angelus.gamedomain.factory.TurnUseCaseFactory
import com.angelus.mapdomain.factory.CurrentMapUseCaseFactory
import com.angelus.playerdomain.factory.PlayerUseCaseFactory

interface GameScreenPageFactory {

    val playerUseCaseFactory: PlayerUseCaseFactory
    val currentMapUseCaseFactory: CurrentMapUseCaseFactory
    val gameUseCaseFactory: TurnUseCaseFactory

    fun makeViewModel(params: GameScreenViewModel.Params): GameScreenViewModel {
        val gameUseCases = GameScreenViewModel.GameUseCases(
            gameUseCaseFactory.makeObserveTurnUseCase(),
            gameUseCaseFactory.makeNextTurnUseCase()
        )
        val playerUseCases = GameScreenViewModel.PlayerUseCases(
            playerUseCaseFactory.makeMovePlayerUseCase(),
            playerUseCaseFactory.makeRotatePlayerUseCase(),
            playerUseCaseFactory.makeObservePlayerUseCase()
        )
        val mapUseCases = GameScreenViewModel.MapUseCases(
            currentMapUseCaseFactory.makeObserveCurrentMapUseCase(),
            currentMapUseCaseFactory.makeFetchPanorameUseCase(),
            currentMapUseCaseFactory.makeCheckMoveInMapUseCase()
        )
        return GameScreenViewModel(
            params = params,
            gameUseCases = gameUseCases,
            playerUseCases = playerUseCases,
            mapUseCases = mapUseCases
        )
    }
    @Composable
    fun MakeGameScreenPage(params: GameScreenViewModel.Params,
                           navigator:GameScreenNavigator) {
        val viewModel = makeViewModel(params)
        return GameScreen(navigator, viewModel)
    }
}