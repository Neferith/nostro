package com.angelus.nostro.page.inventory.mock

import com.angelus.gamedomain.entities.item.Item
import com.angelus.modulea.item.NostroCross

data class InventoryUiState(
    val characters: List<CharacterSummary>,
    val selectedCharacterId: Int,
    val selectedCharacter: CharacterDetails?,
    val container: ContainerInventory
)

data class CharacterSummary(val id: Int, val name: String)
data class CharacterDetails(val id: Int, val name: String, val inventory: List<ItemStack>)
data class ContainerInventory(val name: String, val inventory: List<ItemStack>)

data class ItemStack(val item: Item, val quantity: Int, val originSlot: InventorySlot?)

sealed interface InventorySlot {
    data class Character(val characterId: Int) : InventorySlot
    object Container : InventorySlot
}


val mockInventoryState = InventoryUiState(
    characters = TODO(),
    selectedCharacterId = TODO(),
    selectedCharacter = TODO(),
    container = ContainerInventory(
        name = "FLOOR",
        inventory = listOf(
            ItemStack(
                item = NostroCross,
                quantity = 1,
                originSlot = TODO()
            )
        )
    )
)


