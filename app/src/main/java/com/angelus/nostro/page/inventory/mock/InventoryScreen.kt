package com.angelus.nostro.page.inventory.mock

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InventoryScreen(
    state: InventoryUiState,
    onCharacterSelected: (Int) -> Unit,
    onItemTransfer: (from: InventorySlot, to: InventorySlot, quantity: Int) -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        CharacterSelector(
            characters = state.characters,
            selectedCharacterId = state.selectedCharacterId,
            onCharacterSelected = onCharacterSelected
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.weight(1f)) {
            InventoryPanel(
                title = "Inventaire : ${state.selectedCharacter?.name}",
                items = state.selectedCharacter?.inventory ?: emptyList(),
                /*onItemDrop = { item, quantity ->
                    onItemTransfer(item, InventorySlot.Container, quantity)
                }*/
            )

            Spacer(modifier = Modifier.width(16.dp))

            InventoryPanel(
                title = "Contenant : ${state.container.name}",
                items = state.container.inventory,
               /* onItemDrop = { item, quantity ->
                    onItemTransfer(item, InventorySlot.Character(state.selectedCharacterId), quantity)
                }*/
            )
        }
    }
}
