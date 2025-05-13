package com.angelus.nostro.page.inventory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.angelus.gamedomain.entities.character.Character
import com.angelus.gamedomain.entities.character.fullName
import com.angelus.nostro.page.inventory.mock.CharacterSelector
import com.angelus.nostro.page.inventory.mock.CharacterSummary
import com.angelus.nostro.page.inventory.mock.InventoryPanel
import com.angelus.nostro.ui.theme.FantasyTheme

fun Character.toSumary(index: Int): CharacterSummary {
    return CharacterSummary(
        id = index,
        name = this.description.name.fullName()
    )
}

interface InventoryNavigator

@Composable
fun InventoryScreen(navigator: InventoryNavigator, viewModel: InventoryViewModel) {
    val player by viewModel.currentPlayer.collectAsState()
    val tile by viewModel.currentTile.collectAsState()
    val floorInventory by viewModel.inventoryFloor.collectAsState()

    FantasyTheme {
        Scaffold { paddingValues ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {

                tile?.inventory?.let { inventory ->
                    InventoryPanel(
                        "Sol", floorInventory,
                        onItemDrop = { itemId, quantity ->
                            viewModel.pickUpFromTheFloor(itemId, quantity)
                        }
                    )
                }



                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                ) {
                    player?.band?.characters?.let { characters ->
                        CharacterSelector(
                            characters = characters.mapIndexed { index, character ->
                                character.toSumary(index)
                            },
                            selectedCharacterId = 0,
                        ) { }
                    }

                }
            }
        }
    }
}