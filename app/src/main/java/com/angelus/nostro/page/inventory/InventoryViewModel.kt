package com.angelus.nostro.page.inventory

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelus.mapdomain.entities.Panorama
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.usecase.GetTileAtPosisitionUseCase
import com.angelus.nostro.coordinator.InventoryPosition
import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.usecase.ObservePlayerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class InventoryViewModel(
    startPosition: InventoryPosition,
    useCases: UseCases
): ViewModel() {

    data class UseCases(
        val observePlayerUseCase: ObservePlayerUseCase,
        val getTileAtPositionUseCase: GetTileAtPosisitionUseCase
    )

    val currentPlayer: StateFlow<Player?> = useCases.observePlayerUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    private val _currentTile: MutableStateFlow<Tile?> = MutableStateFlow(null)

    init {
        currentPlayer.onEach { player ->

            if(player == null) {
                null
            } else {
                val tileResult = useCases.getTileAtPositionUseCase(player.entityPosition)
                tileResult.fold(
                    onSuccess = { it },
                    onFailure = { null }
                )
            }
        }.launchIn(viewModelScope)
    }
}