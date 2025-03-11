package com.angelus.nostro.page.newgame.composables

import FantasyTypography
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.angelus.nostro.ui.theme.FantasyColors

@Composable
fun SelectableCard(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .border(
                width = if (isSelected) 3.dp else 1.dp,
                color = if(isSelected) FantasyColors.Accent else FantasyColors.Primary,
                shape = RoundedCornerShape(12.dp),
                )
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
          //  .padding(16.dp), // Padding interne au bouton
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) FantasyColors.Primary else FantasyColors.BackgroundSecondary
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(8.dp) // Ajustable selon design
                .defaultMinSize(minWidth = 100.dp, minHeight = 50.dp) //
        ) {
            Text(
                text = text,
                style = FantasyTypography.titleMedium,
                color = if (isSelected) FantasyColors.onSurface else FantasyColors.onBackgroundSecondary
            )
        }
    }
}