package com.angelus.nostro.section.turn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TurnSection(viewModel: TurnSectionViewModel) {

    val showPlayer by viewModel.showPlayer

    Column(
        modifier = Modifier.background(if(showPlayer) Color.Green else Color.Red)
    ) {
        Text(if(showPlayer) "SHOW PLAYER" else "NOT SHOW PLAYER")
    }

}