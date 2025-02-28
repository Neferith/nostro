package com.angelus.nostro.page.game

import androidx.compose.runtime.Composable
import com.angelus.mapdomain.factory.CurrentMapUseCaseFactory

interface GameScreenPageFactory {

    val playerUseCaseFactory: com.angelus.playerdomain.factory.PlayerUseCaseFactory
    val currentMapUseCaseFactory: com.angelus.mapdomain.factory.CurrentMapUseCaseFactory

    fun makeViewModel(params: GameScreenViewModel.Params): GameScreenViewModel {
        val useCases = GameScreenViewModel.UseCases(
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
            params,
            useCases,
            mapUseCases
        )
    }
    @Composable
    fun MakeGameScreenPage(params: GameScreenViewModel.Params,
                           navigator:GameScreenNavigator) {
        val viewModel = makeViewModel(params)
        return GameScreen(navigator, viewModel)
    }
}