package com.angelus.nostro.page.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.angelus.nostro.component.MoveControls
import kotlin.Suppress

interface  GameScreenNavigator


@Suppress("unused")
@Composable
fun GameScreen(navigator: GameScreenNavigator,
               viewModel: GameScreenViewModel) {
    val playerState = viewModel.currentPlayer.collectAsState()
    val text = remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Joueur : ${playerState.value?.position?.orientation }")
        Text(text = "X : ${playerState.value?.position?.x }")
        Text(text = "Y : ${playerState.value?.position?.y }")
        OutlinedTextField(value = text.value, onValueChange = {
            text.value = it
        })

        MoveControls(
            onMove = {
                viewModel.processMoveAction(it)
            }
        )

    }
}