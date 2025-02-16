package com.angelus.nostro.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

enum class MoveAction {
    FORWARD, BACKWARD,
    STRAFE_LEFT, STRAFE_RIGHT,
    ROTATE_LEFT, ROTATE_RIGHT
}

@Composable
fun MoveButton(icon: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(60.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
    ) {
        Icon(icon, contentDescription = null, tint = Color.White)
    }
}

@Composable
fun MoveControls(onMove: (MoveAction) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            MoveButton(Icons.AutoMirrored.Default.KeyboardArrowLeft) { onMove(MoveAction.ROTATE_LEFT) }  // Rotation à gauche
            MoveButton(Icons.Default.KeyboardArrowUp) { onMove(MoveAction.FORWARD) } // Avancer
            MoveButton(Icons.AutoMirrored.Default.KeyboardArrowRight) { onMove(MoveAction.ROTATE_RIGHT) } // Rotation à droite
        }
        Row {
            MoveButton(Icons.AutoMirrored.Default.KeyboardArrowLeft) { onMove(MoveAction.STRAFE_LEFT) }  // Strafe gauche
            MoveButton(Icons.Default.KeyboardArrowDown) { onMove(MoveAction.BACKWARD) }  // Reculer
            MoveButton(Icons.AutoMirrored.Default.KeyboardArrowRight) { onMove(MoveAction.STRAFE_RIGHT) }  // Strafe droite
        }
    }
}