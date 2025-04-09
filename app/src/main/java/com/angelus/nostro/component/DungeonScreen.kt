package com.angelus.nostro.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.angelus.gamedomain.entities.Position
import com.angelus.mapdomain.entities.Tile
import com.angelus.nostro.component.dungeon.DungeonCanvasView2
import com.angelus.nostro.component.dungeon.DungeonCanvasView3


@Composable
fun DungeonScreen(mapType: String, simpleGrid: List<List<Tile>>, positionInSimpleGrid: Position) {
    val context = LocalContext.current
    val dungeonView = remember { DungeonCanvasView3(context) }.apply {
        updateGrid(mapType,simpleGrid,positionInSimpleGrid)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black).clip(RoundedCornerShape(0.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Affichage de la vue Canvas dans un container Compose
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // La vue prend tout l'espace disponible
            factory = { dungeonView }
        )

        // Boutons de contrôle
       /* Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { dungeonView.rotateRight() }) {
                Text("Tourner →")
            }
            Button(onClick = { dungeonView.moveForward() }) {
                Text("Avancer ↑")
            }
        }*/
    }
}


