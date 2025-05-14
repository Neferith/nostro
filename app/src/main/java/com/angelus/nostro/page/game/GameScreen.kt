package com.angelus.nostro.page.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.angelus.gamedomain.entities.TurnType
import com.angelus.nostro.component.DungeonScreen
import com.angelus.nostro.component.MoveControls
import kotlin.Suppress
import androidx.compose.ui.res.painterResource
import com.angelus.mapdomain.entities.hasInventory
import com.angelus.nostro.R

interface GameScreenNavigator {
    fun goToFloorInventory()
}


@Suppress("unused")
@Composable
fun GameScreen(
    navigator: GameScreenNavigator,
    viewModel: GameScreenViewModel
) {
    val playerState = viewModel.currentPlayer.collectAsState()
    val turnState = viewModel.currentTurn.collectAsState()
    val panoramaState by viewModel.panoramaState
    val text = remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier
            .width(350.dp)
            .height(350.dp)) {
            panoramaState?.let { panorama ->
                DungeonScreen(
                    panorama.mapType,
                    panorama.tiles,
                    panorama.getPositionInSimpleGrid()
                )
            }

            // Bouton superposé
            if(panoramaState?.getCurrentTile()?.hasInventory()?:false) {
                IconButton(
                    onClick = { navigator.goToFloorInventory()},
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.BottomEnd) // Position en bas au centre
                        .zIndex(1f) // Assure que le bouton est au-dessus
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.take_on_floor_low),
                        contentDescription = "Ramasser",
                        modifier = Modifier.fillMaxSize(), // Remplit le bouton sans marge
                        tint = Color.Unspecified // Garde les couleurs d'origine
                    )
                }
            }
        }
        Text(text = "Joueur : ${playerState.value?.entityPosition?.orientation}")
        Text(text = "X : ${playerState.value?.entityPosition?.x}")
        Text(text = "Y : ${playerState.value?.entityPosition?.y}")
        OutlinedTextField(value = text.value, onValueChange = {
            text.value = it
        })

        if (turnState.value?.type == TurnType.PLAYER("")) {
            MoveControls(
                onMove = {
                    viewModel.processMoveAction(it)
                }
            )
        }

    }
}