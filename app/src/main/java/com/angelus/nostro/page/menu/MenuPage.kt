package com.angelus.nostro.page.menu

import FantasyTypography
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.angelus.gamedomain.entities.GameSlot
import com.angelus.nostro.R
import com.angelus.nostro.ui.component.FantasyButton
import com.angelus.nostro.ui.theme.FantasyColors
import com.angelus.nostro.ui.theme.FantasyTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

interface MenuNavigator {
    fun startNewGame(slotId: Int)
    fun continueGame(slotId: Int)
}

@Composable
fun MenuPage(viewModel: MenuViewModel,
             navigator: MenuNavigator) {

    val systemUiController = rememberSystemUiController()
    val statusBarColor = FantasyColors.Primary

    systemUiController.setSystemBarsColor(color = statusBarColor)
    val slots by viewModel.gameSlots.collectAsState()


    FantasyTheme {
        Image(
            painter = painterResource(id = R.drawable.stone_wall_texture),
            contentDescription = "Texture murale sombre et antique",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Pour remplir l'écran sans déformation
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                // Titre du jeu
                Text(
                    text = "NOSTRO",
                    style = FantasyTypography.displayMedium,
                    color = FantasyColors.Accent
                )


                for (slot in slots) {
                    when(slot) {
                        is GameSlot.loadGame ->   FantasyButton(
                            text = "Continuer Partie " + slot.slot,
                            onClick = { navigator.continueGame(slot.slot) }
                        )

                        is GameSlot.newGame ->
                        FantasyButton(
                            text = "Nouvelle Partie " + slot.slot,
                            onClick = { navigator.startNewGame(slot.slot) }
                        )
                    }
                }

            }
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun PreviewMenuScreen() {
    MenuPage(
        viewModel = MenuViewModel(),
        navigator = object : MenuNavigator {
        override fun startNewGame() {
            // Implémentation factice pour la preview
        }
    })
}*/
