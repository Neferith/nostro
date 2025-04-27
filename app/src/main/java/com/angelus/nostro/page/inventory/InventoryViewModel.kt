package com.angelus.nostro.page.inventory

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelus.gamedomain.entities.item.Inventory
import com.angelus.gamedomain.usecase.FetchItemsByIdUseCase
import com.angelus.mapdomain.entities.Panorama
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.usecase.GetTileAtPosisitionUseCase
import com.angelus.nostro.coordinator.InventoryPosition
import com.angelus.nostro.page.inventory.mock.ItemStack
import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.usecase.ObservePlayerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class InventoryViewModel(
    startPosition: InventoryPosition,
    useCases: UseCases
) : ViewModel() {

    data class UseCases(
        val observePlayerUseCase: ObservePlayerUseCase,
        val getTileAtPositionUseCase: GetTileAtPosisitionUseCase,
        val fetchItemsByIdUseCase: FetchItemsByIdUseCase
    )

    val currentPlayer: StateFlow<Player?> = useCases.observePlayerUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val currentTile: StateFlow<Tile?> = currentPlayer
        .mapLatest { player ->
            player?.let {
                useCases.getTileAtPositionUseCase(it.entityPosition).getOrNull()
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val inventoryFloor: StateFlow<List<ItemStack>> = currentTile
        .mapLatest { tile ->
            tile?.inventory?.let { inventory ->
                useCases.fetchItemsByIdUseCase(inventory.items.keys.toTypedArray())
                    .fold(
                        onSuccess = { items ->
                            items.map { item -> ItemStack(item, inventory.items[item.id] ?: 1) }
                        },
                        onFailure = { emptyList() }
                    )
            } ?: emptyList()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}