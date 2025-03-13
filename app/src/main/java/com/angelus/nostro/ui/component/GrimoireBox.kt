package com.angelus.nostro.ui.component

import FantasyTypography
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.angelus.nostro.ui.theme.FantasyColors

@Composable
fun GrimoireOptionBox(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(FantasyColors.BackgroundSecondary)
            .border(2.dp, FantasyColors.Accent, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = FantasyTypography.bodyLarge,
            color = FantasyColors.Primary
        )
    }
}
