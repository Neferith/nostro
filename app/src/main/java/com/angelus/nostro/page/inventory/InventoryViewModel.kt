package com.angelus.nostro.page.inventory

import android.util.Log
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
import kotlinx.coroutines.launch

class InventoryViewModel(
    startPosition: InventoryPosition,
    val dataUseCases: DataUseCases,
    val inventoryUseCases: InventoryUseCases
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
                            items.map { item ->
                                ItemStack(
                                    item,
                                    inventory.items[item.id] ?: 1
                                )
                            }
                        },
                        onFailure = { emptyList() }
                    )
            } ?: emptyList()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val inventoryCharacter: StateFlow<List<ItemStack>> = currentPlayer
        .mapLatest { player ->
            player?.band?.characters?.getOrNull(0)?.inventory?.let { inventory ->
                dataUseCases.fetchItemsByIdUseCase(inventory.items.keys.toTypedArray())
                    .fold(
                        onSuccess = { items ->
                            items.map { item ->
                                ItemStack(
                                    item,
                                    inventory.items[item.id] ?: 1
                                )
                            }
                        },
                        onFailure = { emptyList() }
                    )
            } ?: emptyList()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun pickUpFromTheFloor(objectId: String, quantity: Int) {
        val player = currentPlayer.value ?: return
        val character = player.band.characters.getOrNull(0) ?: return

        viewModelScope.launch {
            val addResult =
                inventoryUseCases.addObjectToPlayerUseCase(character.id, objectId, quantity)
            if (addResult.isFailure) {
                addResult.exceptionOrNull()?.printStackTrace()
                return@launch
            }

            val removeResult = inventoryUseCases.removeObjectToTileUseCase(
                player.entityPosition,
                objectId,
                quantity
            )
            if (removeResult.isFailure) {
                removeResult.exceptionOrNull()?.printStackTrace()
                return@launch
            }

            Log.d("TAG", "OPÉRATION RÉUSSIE")
        }
    }

    fun dropToTheFloor(objectId: String, quantity: Int) {
        val player = currentPlayer.value ?: return
        val character = player.band.characters.getOrNull(0) ?: return

        viewModelScope.launch {
            val addResult =
                inventoryUseCases.addObjectToTileUseCase(player.entityPosition, objectId, quantity)
            if (addResult.isFailure) {
                addResult.exceptionOrNull()?.printStackTrace()
                return@launch
            }

            val removeResult =
                inventoryUseCases.removeObjectToPlayerUseCase(character.id, objectId, quantity)
            if (removeResult.isFailure) {
                removeResult.exceptionOrNull()?.printStackTrace()
                // À ce stade, l'objet est déjà sur le sol, donc potentiellement dupliqué
            }

            Log.d("TAG", "OBJET DÉPOSÉ SUR LE SOL")
        }
    }
}