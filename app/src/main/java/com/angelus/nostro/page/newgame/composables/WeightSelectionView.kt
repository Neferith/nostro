package com.angelus.nostro.page.newgame.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.angelus.gamedomain.entities.CharacterSize
import com.angelus.gamedomain.entities.CharacterWeight
import com.angelus.nostro.ui.component.SelectableCard
import com.angelus.nostro.ui.theme.FantasyColors

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WeightSelector(
    values: List<CharacterWeight>,
    selectedWeight: CharacterWeight?,
    onWeightSelected: (CharacterWeight) -> Unit
) {
    Text(
        text = "Choisissez votre taille",
        color = FantasyColors.onPrimary,
        fontSize = 24.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    FlowRow(
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Espace horizontal entre items
        verticalArrangement = Arrangement.spacedBy(16.dp),   // Espace vertical entre lignes
    ) {
        values.forEach { weight ->
            SelectableCard(
                text = weight.name.lowercase().replaceFirstChar { it.uppercase() }, // "Male", "Female"
                isSelected = weight == selectedWeight,
                onClick = { onWeightSelected(weight) }
            )
        }
    }
}