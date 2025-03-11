package com.angelus.nostro.page.newgame.composables

import FantasyTypography
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angelus.nostro.ui.theme.FantasyColors

@Composable
fun SexSelection(onSelected: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Choisissez le sexe",
            style = FantasyTypography.headlineMedium,
            color = FantasyColors.Primary
        )
        Spacer(Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            GrimoireOptionBox(text = "Masculin", onClick = { onSelected("Masculin") })
            GrimoireOptionBox(text = "Féminin", onClick = { onSelected("Féminin") })
        }
    }
}
