package com.angelus.nostro.section.turn

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry

interface TurnSectionFactory {

    val navBackStackEntry: NavBackStackEntry

    private class TurnSectionViewModelFactory(
        // val params: Params,
       // private val gameUseCases: GameUseCases,
       // private val playerUseCases: PlayerUseCases,
        //private val mapUseCases: MapUseCases
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TurnSectionViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TurnSectionViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


    @Composable
    private fun MakeViewModel(
        navBackStackEntry: NavBackStackEntry,
    ): TurnSectionViewModel {



        return viewModel(
            navBackStackEntry,
            factory = TurnSectionViewModelFactory()
        )
    }


    @Composable
    fun MakeTurnSection(
    ) {
        val viewModel = MakeViewModel(navBackStackEntry)
        return TurnSection(viewModel)
    }
}