package com.angelus.nostro.utils

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.Log



/**
 * Dessine une forme de mur en perspective avec 4 points.
 * @param top Coordonnées du point supérieur, représente le fond (x, y).
 * @param bottom Coordonnées du point inférieur, représente le fond (x, y).
 * @param bottomFront Coordonnées du point inférieur, représente l'avant (x, y).
 * @param topFront Coordonnées du point supérieur, représente l'avant (x, y).
 */
fun Path.drawPerspectiveWallShape(
    top: WallPoint,
    bottom: WallPoint,
    bottomFront: WallPoint,
    topFront: WallPoint
) {
    // On commence par le point de départ au fond
    moveTo(top.x, top.y)

    // Trace une ligne vers le bas
    lineTo(bottom.x, bottom.y)

    // Ajoute la profondeur, vers l'avant et le bas
    lineTo(bottomFront.x, bottomFront.y)

    // Remonte en profondeur, vers l'avant et le bas
    lineTo(topFront.x, topFront.y)
    close()
}