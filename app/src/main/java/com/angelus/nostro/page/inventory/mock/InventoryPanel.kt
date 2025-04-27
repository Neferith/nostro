package com.angelus.nostro.page.inventory.mock

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun InventoryPanel(
    title: String,
    items: List<ItemStack>,
    //onItemDrop: (item: InventorySlot, quantity: Int) -> Unit
) {
    Column(
        modifier = Modifier
           // .weight(1f)
            .border(1.dp, Color.DarkGray)
            .padding(8.dp)
    ) {
        Text(title, style = MaterialTheme.typography.titleMedium)

        LazyColumn {
            items(items) { itemStack ->
                InventoryItemRow(itemStack/*, onItemDrop*/)
            }
        }
    }
}