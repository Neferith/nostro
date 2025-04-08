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
import com.angelus.gamedomain.entities.character.CharacterSensitivity
import com.angelus.nostro.ui.component.SelectableCard
import com.angelus.nostro.ui.theme.FantasyColors

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SensitivitySelector (
    values: List<CharacterSensitivity>,
    selectedSensitivity: CharacterSensitivity?,
    onSensitivitySelected: (CharacterSensitivity) -> Unit
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
        values.forEach { sensitivity ->
            SelectableCard(
                text = sensitivity.name.lowercase().replaceFirstChar { it.uppercase() }, // "Male", "Female"
                isSelected = sensitivity == selectedSensitivity,
                onClick = { onSensitivitySelected(sensitivity) }
            )
        }
    }
}