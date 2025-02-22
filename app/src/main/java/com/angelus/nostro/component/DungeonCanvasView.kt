package com.angelus.nostro.component

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.View
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.angelus.nostro.R

class DungeonCanvasView(context: Context) : View(context) {

    private val paintWall = Paint().apply {
        color = Color.DKGRAY
        style = Paint.Style.FILL
    }

    private val paintWallLeft = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    private val paintWallRigt = Paint().apply {
        color = Color.YELLOW
        style = Paint.Style.FILL
    }

    private val paintFloor = Paint().apply {
        color = Color.LTGRAY
        style = Paint.Style.FILL
    }

    private val dungeonGrid = arrayOf(
        intArrayOf(1, 1, 1, 1, 1),
        intArrayOf(1, 0, 0, 0, 1),
        intArrayOf(1, 0, 0, 0, 1),
        intArrayOf(1, 0, 0, 0, 1),
        intArrayOf(1, 1, 1, 1, 1)
    )

    private var playerX = 1
    private var playerY = 2
    private var direction = 0 // 0 = Nord, 1 = Est, 2 = Sud, 3 = Ouest

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()

        // Dessin du sol
        canvas.drawRect(0f, height / 2, width, height, paintFloor)

        // Dessiner la perspective des murs
        drawDungeonPerspective(canvas, width, height)
    }

    private fun drawDungeonPerspective(canvas: Canvas, screenWidth: Float, screenHeight: Float) {
        val centerX = screenWidth / 2
        val centerY = screenHeight / 2
        val wallWidth = screenWidth / 2 // Largeur de base des murs
        val wallHeight = screenHeight / 2

        for (depth in 1..2) {
            val scale = 1f / depth
            val halfWall = (wallWidth * scale) / 2

            val left = centerX - halfWall
            val right = centerX + halfWall
            val top = centerY - (wallHeight * scale) / 2
            val bottom = centerY + (wallHeight * scale) / 2


            // Vérifier et dessiner les murs latéraux
            // Vérifier si un mur est devant
            val (wallX, wallY) = getForwardPosition(playerX, playerY, direction, depth)
            val (wallXLat, wallYLat) = getForwardPosition(playerX, playerY, direction, depth-1)
            // On ne prend pas les bons murs latéraux
            val leftWallPos = getSidePosition(wallXLat, wallYLat, direction, -1) // Gauche
            val rightWallPos = getSidePosition(wallXLat, wallYLat, direction, 1)  // Droite

            if (isWall(leftWallPos)) {
            drawTrapezoid(canvas, left, right, top, bottom, true, depth) // Mur gauche
             }
            if (isWall(rightWallPos)) {

                drawTrapezoid(canvas, left, right, top, bottom, false, depth) // Mur droit
        }
            //}


            if (wallY in dungeonGrid.indices && wallX in dungeonGrid[0].indices && dungeonGrid[wallY][wallX] == 1) {
                canvas.drawRect(left, top, right, bottom, paintWall)
                break
            }
        }
    }

    // Dessine un mur en perspective (trapezoïde)
    private fun drawTrapezoid(canvas: Canvas, left: Float, right: Float, top: Float, bottom: Float, isLeft: Boolean, depth: Int) {
        val wallPaint = Paint().apply {
            color = Color.YELLOW
            style = Paint.Style.FILL
        }

        val wallWidthMock = width.toFloat() / (2 * depth)
        val wallHeightMock = height.toFloat()/ (2 * depth)
        val path = Path()
        if (isLeft) {
            path.moveTo(left, top)
            path.moveTo(left, bottom)
            //path.lineTo(left -wallWidthMock, bottom)       // Bas intérieur
            path.lineTo(left - wallWidthMock/2, bottom + wallWidthMock/2 )  // Point bas gauche
            path.lineTo(left - wallWidthMock/2, top - wallWidthMock/2 )

            path.lineTo(left , top)          // Retour en haut
        } else {
            path.moveTo(right, top)
            path.lineTo(right, bottom)      // Bas intérieur
            path.lineTo(right + wallWidthMock/2, bottom + wallWidthMock/2) // Point bas droit
            path.lineTo(right + wallWidthMock/2, top - wallWidthMock/2) // Point bas droit
            path.lineTo(right, top)         // Retour en haut
        }

        path.close()
        val texture = BitmapFactory.decodeResource(resources, R.drawable.brick)
        canvas.drawPath(path, wallPaint)
    }

    private fun getForwardPosition(x: Int, y: Int, dir: Int, depth: Int): Pair<Int, Int> {
        return when (dir) {
            0 -> Pair(x, y - depth) // Nord
            1 -> Pair(x + depth, y) // Est
            2 -> Pair(x, y + depth) // Sud
            3 -> Pair(x - depth, y) // Ouest
            else -> Pair(x, y)
        }
    }

    // Retourne la position d'un mur latéral
    private fun getSidePosition(x: Int, y: Int, dir: Int, side: Int): Pair<Int, Int> {
        return when (dir) {
            0 -> Pair(x + side, y) // Nord (latéraux = Est/Ouest)
            1 -> Pair(x, y + side) // Est  (latéraux = Nord/Sud)
            2 -> Pair(x + side, y) // Sud  (latéraux = Est/Ouest)
            3 -> Pair(x, y - side) // Ouest (latéraux = Nord/Sud)
            else -> Pair(x, y)
        }
    }

    // Vérifie si une case contient un mur
    private fun isWall(pos: Pair<Int, Int>): Boolean {
        val (x, y) = pos
        return y in dungeonGrid.indices && x in dungeonGrid[0].indices && dungeonGrid[y][x] == 1
    }

  /*  fun moveForward() {
        val (newX, newY) = getForwardPosition(playerX, playerY, direction, 1)
        if (!isWall(newX, newY)) {
            playerX = newX
            playerY = newY
            invalidate() // Redessiner
        }
    }

    fun rotateRight() {
        direction = (direction + 1) % 4
        invalidate() // Redessiner
    }

    fun rotateLeft() {
        direction = (direction + 3) % 4 // Équivalent de -1 modulo 4
        invalidate() // Redessiner
    }*/
}