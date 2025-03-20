package com.angelus.nostro.page.newgame.composables

import androidx.compose.runtime.Composable
import com.angelus.nostro.ui.component.FantasyTextField

@Composable
fun NameSelectionView(
    firstname: String,
    lastname: String,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit
) {
    FantasyTextField(
        value = firstname,
        onValueChange = {
            onFirstNameChange(it)
        }
    )
    FantasyTextField(
        value = lastname,
        onValueChange = {
            onLastNameChange(it)
        }
    )

}