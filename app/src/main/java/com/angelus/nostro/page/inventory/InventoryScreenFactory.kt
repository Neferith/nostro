package com.angelus.nostro.page.inventory

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.gamedomain.factory.TurnUseCaseFactory
import com.angelus.mapdomain.factory.CurrentMapUseCaseFactory
import com.angelus.nostro.coordinator.InventoryPosition
import com.angelus.playerdomain.factory.PlayerUseCaseFactory

interface InventoryScreenFactory  {

    val playerUseCaseFactory: PlayerUseCaseFactory
    val currentMapUseCaseFactory: CurrentMapUseCaseFactory
    val gameUseCaseFactory: TurnUseCaseFactory
    val currentGameUseCaseFactory: CurrentGameUseCaseFactory

    private class InventoryViewModelFactory(
        val position: InventoryPosition,
        private val useCases: InventoryViewModel.UseCases,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return InventoryViewModel(position,useCases
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    @Composable
    private fun MakeViewModel(
        navBackStackEntry: NavBackStackEntry, position: InventoryPosition
    ): InventoryViewModel {

        val useCases = InventoryViewModel.UseCases(
            observePlayerUseCase = playerUseCaseFactory.makeObservePlayerUseCase(),
            getTileAtPositionUseCase = currentMapUseCaseFactory.makeGetTileAtPosisitionUseCase(),
            fetchItemsByIdUseCase = currentGameUseCaseFactory.makeFetchItemsByIdUseCase()
        )

        return viewModel(
            navBackStackEntry,
            factory = InventoryViewModelFactory( position,
                useCases
            )
        )
    }

    @Composable
    fun MakeInventoryScreenPage(
        position: InventoryPosition,
        navigator: InventoryNavigator,
        navBackStackEntry: NavBackStackEntry
    ) {
        val viewModel = MakeViewModel(navBackStackEntry, position)
        return InventoryScreen(navigator, viewModel)
    }
}