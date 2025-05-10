package com.angelus.nostro.page.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelus.gamedomain.usecase.FetchItemsByIdUseCase
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.usecase.AddObjectToTileUseCase
import com.angelus.mapdomain.usecase.GetTileAtPosisitionUseCase
import com.angelus.mapdomain.usecase.RemoveObjectToTileUseCase
import com.angelus.nostro.coordinator.InventoryPosition
import com.angelus.nostro.page.inventory.mock.ItemStack
import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.usecase.AddObjectToPlayerUseCase
import com.angelus.playerdomain.usecase.ObservePlayerUseCase
import com.angelus.playerdomain.usecase.RemoveObjectToPlayerUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class InventoryViewModel(
    startPosition: InventoryPosition,
    dataUseCases: DataUseCases,
    inventoryUseCases: InventoryUseCases
) : ViewModel() {

    data class DataUseCases(
        val observePlayerUseCase: ObservePlayerUseCase,
        val getTileAtPositionUseCase: GetTileAtPosisitionUseCase,
        val fetchItemsByIdUseCase: FetchItemsByIdUseCase
    )

    data class InventoryUseCases(
        val addObjectToTileUseCase: AddObjectToTileUseCase,
        val removeObjectToTileUseCase: RemoveObjectToTileUseCase,
        val addObjectToPlayerUseCase: AddObjectToPlayerUseCase,
        val removeObjectToPlayerUseCase: RemoveObjectToPlayerUseCase
    )

    val currentPlayer: StateFlow<Player?> = dataUseCases.observePlayerUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val currentTile: StateFlow<Tile?> = currentPlayer
        .mapLatest { player ->
            player?.let {
                dataUseCases.getTileAtPositionUseCase(it.entityPosition).getOrNull()
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val inventoryFloor: StateFlow<List<ItemStack>> = currentTile
        .mapLatest { tile ->
            tile?.inventory?.let { inventory ->
                dataUseCases.fetchItemsByIdUseCase(inventory.items.keys.toTypedArray())
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