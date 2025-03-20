package com.angelus.nostro.ui.component

import FantasyTypography
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angelus.gamedomain.entities.Attributes
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.nostro.ui.theme.FantasyColors

@Composable
fun AttributesPreview(currentAttributes: Attributes?, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = FantasyColors.Surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Attributs",
                style = FantasyTypography.titleSmall,
                color = FantasyColors.Surface
            )
            if (currentAttributes != null) {

                AttributeRow("Musculature", currentAttributes.musculature.permanent)
                AttributeRow("Flexibilité", currentAttributes.flexibility.permanent)
                AttributeRow("Intelligence", currentAttributes.brain.permanent)
                AttributeRow("Vitalité", currentAttributes.vitality.permanent)
            } else {
                Text("Sélectionnez un genre pour voir les attributs", color = FantasyColors.Surface)
            }
        }
    }
}