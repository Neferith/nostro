package com.angelus.nostro.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.*

class DungeonCanvasView3(context: Context) : View(context) {
    private val paint = Paint()

    // Carte (1 = mur, 0 = vide)
    private val map = arrayOf(
        intArrayOf(1, 1, 1, 1, 1, 1),
        intArrayOf(1, 0, 0, 0, 0, 1),
        intArrayOf(1, 0, 1, 0, 0, 1),
        intArrayOf(1, 0, 0, 0, 0, 1),
        intArrayOf(1, 1, 1, 1, 1, 1)
    )

    private var playerX = 2.5f
    private var playerY = 2.5f
    private var playerAngle = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val screenWidth = width
        val screenHeight = height
        val fov = Math.PI / 3  // Champ de vision de 60°
        val numRays = screenWidth  // Un rayon par colonne

        // Dessiner le fond
        canvas.drawColor(Color.BLACK)

        for (i in 0 until numRays) {
            val rayAngle = (playerAngle - fov / 2.0 + (fov * i / numRays)).toFloat()

            var distance = 0f
            val maxDepth = 20
            var rayX = playerX
            var rayY = playerY

            val dx = cos(rayAngle) * 0.1f
            val dy = sin(rayAngle) * 0.1f

            while (distance < maxDepth) {
                rayX += dx
                rayY += dy
                distance += 0.1f

                val mapX = rayX.toInt()
                val mapY = rayY.toInt()

                if (mapX < 0 || mapY < 0 || mapX >= map[0].size || mapY >= map.size) break

                if (map[mapY][mapX] == 1) {
                    break
                }
            }

            // Calcul de la hauteur du mur
            val wallHeight = (screenHeight / (distance + 0.1)).toInt()
            val top = (screenHeight / 2 - wallHeight / 2).toFloat()
            val bottom = (screenHeight / 2 + wallHeight / 2).toFloat()

            // Ombre selon la distance
            val shade = (255 - (distance * 12)).toInt().coerceIn(0, 255)
            paint.color = Color.rgb(shade, shade, shade)

            // Dessiner la colonne
            canvas.drawLine(i.toFloat(), top, i.toFloat(), bottom, paint)
        }
    }
}