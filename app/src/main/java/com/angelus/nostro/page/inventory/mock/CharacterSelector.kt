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
import androidx.compose.foundation.layout.padding
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
    Row(modifier = Modifier.fillMaxWidth()) {
        val displayedCharacters = characters + List(4 - characters.size) { CharacterSummary(id = -1, name = "", fake = true) }

        displayedCharacters.forEach { character ->
            Column(horizontalAlignment = Alignment.Start) {
                val isValidCharacter = !character.fake
                // Icône ou portrait
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(width = 48.dp, height = 76.dp)
                        .background(if (isValidCharacter && character.id == selectedCharacterId) Color.Cyan else Color.Gray)
                        .clickable(
                            enabled = isValidCharacter,
                            onClick = { if (isValidCharacter) onCharacterSelected(character.id) }
                        )
                ) {
                    if (isValidCharacter) {
                        Text(
                            text = character.name.first().toString(),
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.titleSmall
                        )
                    } else {
                        // Afficher un indicateur ou un texte pour les éléments vides
                        Text(
                            text = "Vide",  // Texte ou image pour un élément vide
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray)
                        )
                    }
                }
                if (isValidCharacter) {
                    Text(character.name, fontSize = 12.sp)
                }
            }
        }
    }
}