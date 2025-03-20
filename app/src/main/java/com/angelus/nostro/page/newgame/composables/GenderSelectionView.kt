package com.angelus.nostro.page.newgame.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.angelus.gamedomain.entities.CharacterGender
import com.angelus.nostro.ui.component.SelectableCard
import com.angelus.nostro.ui.theme.FantasyColors

@Composable
 fun GenderSelector(
    selectedGender: CharacterGender?,
    onGenderSelected: (CharacterGender) -> Unit
) {
    Text(
        text = "Choisissez votre sexe",
        color = FantasyColors.onPrimary,
        fontSize = 24.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        CharacterGender.entries.forEach { gender ->
            SelectableCard(
                text = gender.name.lowercase().replaceFirstChar { it.uppercase() }, // "Male", "Female"
                isSelected = gender == selectedGender,
                onClick = { onGenderSelected(gender) }
            )
        }
    }
}
