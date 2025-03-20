package com.angelus.nostro.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.angelus.nostro.ui.theme.FantasyColors

@Composable
fun AttributeRow(name: String, value: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(name, color = FantasyColors.onSurface)
        Text(
            text = if (value >= 0) "+$value" else "$value",
            color = if (value >= 0) Color.Green else Color.Red
        )
    }
}