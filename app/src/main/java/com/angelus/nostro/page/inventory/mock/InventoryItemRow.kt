package com.angelus.nostro.page.inventory.mock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InventoryItemRow(
    itemStack: ItemStack,
  //  onItemDrop: (InventorySlot, Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("${itemStack.item.title} x${itemStack.quantity}")
        Button(onClick = {
           // onItemDrop(itemStack.originSlot, 1) // exemple : transfert de 1 item
        }) {
            Text("→")
        }
    }
}