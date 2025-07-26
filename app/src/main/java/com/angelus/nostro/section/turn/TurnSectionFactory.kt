package com.angelus.nostro.section.turn

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.angelus.faction.domain.factory.FactionUseCasFactory
import com.angelus.npc.domain.factory.TurnUseCaseFactory
import com.angelus.mapdomain.factory.CurrentMapUseCaseFactory
import com.angelus.nostro.section.turn.TurnSectionViewModel.GameUseCases
import com.angelus.playerdomain.factory.PlayerUseCaseFactory

interface TurnSectionFactory {

    val navBackStackEntry: NavBackStackEntry

    val playerUseCaseFactory: PlayerUseCaseFactory
    val currentMapUseCaseFactory: CurrentMapUseCaseFactory
    val gameUseCaseFactory: TurnUseCaseFactory
    val factionUseCaseFactory: FactionUseCasFactory

    private class TurnSectionViewModelFactory(
        val gameUseCases: GameUseCases
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TurnSectionViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TurnSectionViewModel(gameUseCases) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


    @Composable
    private fun MakeViewModel(
        navBackStackEntry: NavBackStackEntry,
    ): TurnSectionViewModel {

        val gameUseCases = GameUseCases(
            getPlayerUseCase = playerUseCaseFactory.makeGetPlayerUseCase(),
            observeTurnUseCase = gameUseCaseFactory.makeObserveTurnUseCase(),
            nextTurnUseCase = gameUseCaseFactory.makeNextTurnUseCase(),
            checkVisibilityUseCase = currentMapUseCaseFactory.makeCheckVisibilityUseCase(),
            checkFactionUseCase = factionUseCaseFactory.makeCheckFactionUseCase()
        )
        return viewModel(
            navBackStackEntry,
            factory = TurnSectionViewModelFactory(gameUseCases)
        )
    }


    @Composable
    fun MakeTurnSection(
    ) {
        val viewModel = MakeViewModel(navBackStackEntry)
        return TurnSection(viewModel)
    }
}