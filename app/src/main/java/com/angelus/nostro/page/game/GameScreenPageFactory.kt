package com.angelus.nostro.page.game

import androidx.compose.runtime.Composable
import com.angelus.gamedomain.factory.PlayerUseCaseFactory

interface GameScreenPageFactory {

    val playerUseCaseFactory: PlayerUseCaseFactory

    fun makeViewModel(params: GameScreenViewModel.Params): GameScreenViewModel {
        val useCases = GameScreenViewModel.UseCases(
            playerUseCaseFactory.makeMovePlayerUseCase(),
            playerUseCaseFactory.makeRotatePlayerUseCase(),
            playerUseCaseFactory.makeObservePlayerUseCase()
        )
        return GameScreenViewModel(params, useCases)
    }
    @Composable
    fun MakeGameScreenPage(params: GameScreenViewModel.Params,
                           navigator:GameScreenNavigator) {
        val viewModel = makeViewModel(params)
        return GameScreen(navigator, viewModel)
    }
}