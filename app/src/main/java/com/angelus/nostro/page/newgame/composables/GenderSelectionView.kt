package com.angelus.nostro.page.newgame.composables

import FantasyTypography
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.CharacterGender
import com.angelus.nostro.ui.theme.BackgroundSecondary
import com.angelus.nostro.ui.theme.FantasyColors

@Composable
fun GenderSelectionContent(
    selectedGender: CharacterGender?,
    onGenderSelected: (CharacterGender) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth()
    ) {
        val isExpanded = maxWidth > 600.dp  // Seuil à ajuster selon design

        if (isExpanded) {
            // Mode paysage / large : disposition en ligne
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    GenderSelector(
                        selectedGender = selectedGender,
                        onGenderSelected = onGenderSelected
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                AttributesPreview(selectedGender = selectedGender, modifier = Modifier.weight(1f))
            }
        } else {
            // Mode portrait : tout en colonne
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GenderSelector(
                    selectedGender = selectedGender,
                    onGenderSelected = onGenderSelected
                )
                Spacer(modifier = Modifier.height(16.dp))
                AttributesPreview(selectedGender = selectedGender)
            }
        }
    }
}

@Composable
private fun AttributesPreview(selectedGender: CharacterGender?, modifier: Modifier = Modifier) {
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
            if (selectedGender != null) {
                val mod = selectedGender.modifier
                AttributeRow("Musculature", mod.musculature)
                AttributeRow("Flexibilité", mod.flexibility)
                AttributeRow("Intelligence", mod.brain)
                AttributeRow("Vitalité", mod.vitality)
            } else {
                Text("Sélectionnez un genre pour voir les attributs", color = FantasyColors.Surface)
            }
        }
    }
}

@Composable
private fun AttributeRow(name: String, value: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(name, color = FantasyColors.Surface)
        Text(
            text = if (value >= 0) "+$value" else "$value",
            color = if (value >= 0) Color.Green else Color.Red
        )
    }
}

@Composable
private fun GenderSelector(
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
        CharacterGender.values().forEach { gender ->
            SelectableCard(
                text = gender.name.lowercase().replaceFirstChar { it.uppercase() }, // "Male", "Female"
                isSelected = gender == selectedGender,
                onClick = { onGenderSelected(gender) }
            )
        }
    }
}

@Composable
fun GenderSelectionView(
    selectedGender: CharacterGender?,
    onGenderSelected: (CharacterGender) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Choisissez votre sexe",
            color = FantasyColors.onPrimary,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CharacterGender.values().forEach { gender ->
                val isSelected = gender == selectedGender
                GenderOptionItem(
                    gender = gender,
                    isSelected = isSelected,
                    onClick = { onGenderSelected(gender) }
                )
            }
        }

        selectedGender?.let { gender ->
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Effets sur vos attributs :",
                color = FantasyColors.onBackground,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            AttributesModifierView(gender.modifier)
        }
    }
}

@Composable
fun GenderOptionItem(
    gender: CharacterGender,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) FantasyColors.Primary else FantasyColors.Surface
    val backgroundColor = if (isSelected) FantasyColors.Surface else BackgroundSecondary

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .border(2.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = gender.name.lowercase().replaceFirstChar { it.uppercase() }, // "Male" ou "Female"
            color = FantasyColors.onBackground,
            fontSize = 18.sp
        )
    }
}

@Composable
fun AttributesModifierView(modifier: AttributesModifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AttributeItem("Musculature", modifier.musculature)
        AttributeItem("Souplesse", modifier.flexibility)
        AttributeItem("Intellect", modifier.brain)
        AttributeItem("Vitalité", modifier.vitality)
    }
}

@Composable
fun AttributeItem(label: String, value: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            color = FantasyColors.onBackground,
            fontSize = 16.sp
        )
        Text(
            text = if (value >= 0) "+$value" else "$value",
            color = if (value >= 0) Color(0xFF85B55E) else Color(0xFFB55E5E), // Vert si bonus, rouge si malus
            fontSize = 16.sp
        )
    }
}