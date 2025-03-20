package com.angelus.nostro.ui.component

import FantasyTypography
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.angelus.nostro.ui.theme.FantasyColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Text


@Composable
fun FantasyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        textStyle = FantasyTypography.titleMedium.copy(color = FantasyColors.Text),
        colors = TextFieldDefaults.colors(
            focusedTextColor = FantasyColors.Text,
            unfocusedTextColor = FantasyColors.Text,
            cursorColor = FantasyColors.Accent,
            focusedContainerColor = FantasyColors.Surface,
            unfocusedContainerColor = FantasyColors.Surface,
            focusedIndicatorColor = FantasyColors.Accent,
            unfocusedIndicatorColor = FantasyColors.Primary
        ),
        label = { Text("Nom du héros", color = FantasyColors.Text) },
        shape = RoundedCornerShape(12.dp)
    )
}
