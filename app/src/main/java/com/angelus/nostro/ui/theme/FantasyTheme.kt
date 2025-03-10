package com.angelus.nostro.ui.theme

import FantasyTypography
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkFantasyColorScheme = darkColorScheme(
    primary = FantasyColors.Primary,
    secondary = FantasyColors.Accent,
    background = FantasyColors.Background,
    surface = FantasyColors.Surface,
    onPrimary = FantasyColors.Text,
    onSecondary = FantasyColors.Text,
    onBackground = FantasyColors.Text,
    onSurface = FantasyColors.Text,
    error = FantasyColors.Danger,
    onError = FantasyColors.Text
)

@Composable
fun FantasyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkFantasyColorScheme,
        typography = FantasyTypography,
        content = content
    )
}