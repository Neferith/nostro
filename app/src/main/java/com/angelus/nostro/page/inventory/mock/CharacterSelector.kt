package com.angelus.nostro.page.inventory.mock

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

@Composable
fun CharacterSelector(
    characters: List<CharacterSummary>,
    selectedCharacterId: Int,
    onCharacterSelected: (Int) -> Unit
) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        characters.forEach { character ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Icône ou portrait
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(if (character.id == selectedCharacterId) Color.Cyan else Color.Gray)
                        .clickable { onCharacterSelected(character.id) }
                ) {
                    Text(
                        text = character.name.first().toString(),
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Text(character.name, fontSize = 12.sp)
            }
        }
    }
}