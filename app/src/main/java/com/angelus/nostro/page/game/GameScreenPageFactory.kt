package com.angelus.nostro.page.game

import androidx.compose.runtime.Composable
import com.angelus.gamedomain.factory.GameDomainUseCaseFactory

interface GameScreenPageFactory {

    val gameDomainUseCaseFactory: GameDomainUseCaseFactory

    fun makeViewModel(params: GameScreenViewModel.Params): GameScreenViewModel {
        val useCases = GameScreenViewModel.UseCases(
            gameDomainUseCaseFactory.makeMovePlayerUseCase(),
            gameDomainUseCaseFactory.makeRotatePlayerUseCase(),
            gameDomainUseCaseFactory.makeObservePlayerUseCase()
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