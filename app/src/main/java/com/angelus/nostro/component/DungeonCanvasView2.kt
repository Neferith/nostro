package com.angelus.nostro.component

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.Log
import android.view.View
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.Offset
import com.angelus.gamedomain.entities.Position
import com.angelus.nostro.R
import com.angelus.nostro.utils.PerspectiveWallDrawer
import com.angelus.nostro.utils.WallPoints
import com.angelus.nostro.utils.drawPerspectiveWallShape

data class DungeonSquare(
    val leftBack: Float,
    val topBack: Float,
    val rightBack: Float,
    val bottomBack: Float,

    val leftForward: Float,
    val topForward: Float,
    val rightFoward: Float,
    val bottomForward: Float
)

class DungeonCanvasView2(context: Context) : View(context) {

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
        style = Paint.Style.STROKE    }

    private var dungeonGrid = arrayOf(
        intArrayOf(1, 1, 1, 1, 1),
        intArrayOf(1, 0, 0, 1, 1),
        intArrayOf(1, 0, 0, 1, 1),
        intArrayOf(1, 1, 1, 1, 1),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(1, 1, 1, 1, 1),
    )

    private var playerX = 1
    private var playerY = 4
    private var direction = 0 // 0 = Nord, 1 = Est, 2 = Sud, 3 = Ouest

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var width = if(height> width)  width.toFloat() else height.toFloat()
        var height = if(height> width)  width.toFloat() else height.toFloat()//height.toFloat()

        val squareSize = minOf(width, height) * 0.75f // Moitié de la vue
        val left = (width - squareSize) * 0.5f
        val top = (height - squareSize) * 0.5f
        val right = left + squareSize
        val bottom = top + squareSize

        val squareWidth = Size(width, height)
        Log.d("DUNGEON"," TEST " + dungeonGrid[playerX][playerY])

        incrementSquare(canvas, squareWidth, 0.75f, deep = dungeonGrid.size - 1)

        val path = Path().apply {
            moveTo(100f, 0f) // Début du path
            lineTo(200f, 0f) // Autre ligne
            lineTo(100f, 100f) // Autre ligne
            lineTo(0f, 100f) // Ligne vers un point

            close() // Ferme la forme (revient au point initial)
        }
        canvas.drawPath(path, paintWallLeft)

    }

    fun incrementSquare(
        canvas: Canvas,
                        oldSquareWidth: Size,
                        ratio: Float,
                        deep: Int
    ) {

        val newSquareWidth = Size(oldSquareWidth.width*ratio, oldSquareWidth.height*ratio)

        //val squareSize = minOf(sWidth, sHeight) * ratio // Moitié de la vue
        val left = (width - newSquareWidth.width) * 0.5f
        val top = (height - newSquareWidth.height) * 0.5f
        val right = left + newSquareWidth.width
        val bottom = top + newSquareWidth.height
        val dungeonSquare = DungeonSquare(
            leftBack = left,
            topBack = top,
            rightBack = right,
            bottomBack = bottom,
            leftForward = (width - oldSquareWidth.width) * 0.5f,
            topForward = (width - oldSquareWidth.width) * 0.5f,
            rightFoward = (width - oldSquareWidth.width) * 0.5f + oldSquareWidth.width,
            bottomForward = (width - oldSquareWidth.width) * 0.5f + oldSquareWidth.height
        )

        if(deep > 0) {
            incrementSquare(canvas, newSquareWidth, 0.75f, deep -1)
        }

        val index = playerY - (dungeonGrid.size -1 - deep)

        Log.d("DUNGEON"," HOP " + index)

        var leftDungeonSquare = dungeonSquare
        for (posX in playerX -1 downTo 0) {

            leftDungeonSquare = DungeonSquare(
                leftBack = leftDungeonSquare.leftBack - newSquareWidth.width,
                topBack = leftDungeonSquare.topBack,
                rightBack = leftDungeonSquare.rightBack - newSquareWidth.width,
                bottomBack = leftDungeonSquare.bottomBack,
                leftForward = leftDungeonSquare.leftForward -oldSquareWidth.width,
                topForward = leftDungeonSquare.topForward,
                rightFoward = leftDungeonSquare.rightFoward -oldSquareWidth.width,
                bottomForward = leftDungeonSquare.bottomForward
            )

            if(dungeonGrid[index][posX] == 1) {

                val path = Path().apply {
                    moveTo(leftDungeonSquare.rightFoward, leftDungeonSquare.topForward) // Début du path
                    lineTo(leftDungeonSquare.rightBack, leftDungeonSquare.topBack) // Ligne vers un point
                    lineTo(leftDungeonSquare.rightBack, leftDungeonSquare.bottomBack) // Autre ligne
                    lineTo(leftDungeonSquare.rightFoward, leftDungeonSquare.bottomForward) // Autre ligne
                    close() // Ferme la forme (revient au point initial)
                }
                canvas.drawPath(path, paintWallLeft)
                canvas.drawRect(
                    leftDungeonSquare.leftForward ,
                    leftDungeonSquare.topForward,
                    leftDungeonSquare.rightFoward,
                    leftDungeonSquare.bottomForward,
                    paintWall
                )

            } else {
             //   canvas.drawRect(left, top, right, bottom, paintFloor)
            }

        }

        var rightDungeonSquare = dungeonSquare
        for (posX in playerX +1 .. dungeonGrid[index].size -1) {

            rightDungeonSquare = DungeonSquare(
                leftBack = rightDungeonSquare.leftBack + newSquareWidth.width,
                topBack = rightDungeonSquare.topBack,
                rightBack = rightDungeonSquare.rightBack + newSquareWidth.width,
                bottomBack = rightDungeonSquare.bottomBack,
                leftForward = rightDungeonSquare.leftForward + oldSquareWidth.width,
                topForward = rightDungeonSquare.topForward,
                rightFoward = rightDungeonSquare.rightFoward + oldSquareWidth.width,
                bottomForward = rightDungeonSquare.bottomForward
            )
            if(dungeonGrid[index][posX] == 1) {

                val path = Path().apply {
                    moveTo(rightDungeonSquare.leftForward, rightDungeonSquare.topForward) // Début du path
                    lineTo(rightDungeonSquare.leftBack, rightDungeonSquare.topBack) // Ligne vers un point
                    lineTo(rightDungeonSquare.leftBack, rightDungeonSquare.bottomBack) // Autre ligne
                    lineTo(rightDungeonSquare.leftForward, rightDungeonSquare.bottomForward) // Autre ligne
                    close() // Ferme la forme (revient au point initial)
                }
                canvas.drawPath(path, paintWallLeft)

                canvas.drawRect(
                    rightDungeonSquare.leftForward ,
                    rightDungeonSquare.topForward,
                    rightDungeonSquare.rightFoward,
                    rightDungeonSquare.bottomForward,
                    paintWall
                )

            }

        }

        // Dessin du sol
        if(dungeonGrid[index][playerX] == 1) {
            canvas.drawRect(dungeonSquare.leftForward, dungeonSquare.topForward, dungeonSquare.rightFoward, dungeonSquare.bottomForward, paintWall)
        } else {
            canvas.drawRect(left, top, right, bottom, paintFloor)
        }

    }

    private fun createWallPath(wallPoints: WallPoints): Path {
        return Path().apply {
            drawPerspectiveWallShape(
                top = wallPoints.top,
                bottom = wallPoints.bottom,
                bottomFront = wallPoints.bottomDepth,
                topFront = wallPoints.topDepth
            )
        }
    }


    private fun drawDungeonPerspective(canvas: Canvas, screenWidthOrigin: Float, screenHeightOrigin: Float) {
        val size = Size(screenWidthOrigin, screenHeightOrigin)

        val squareSize = size.minDimension / 2 // Taille du carré (la moitié de la vue)
        val topLeft = Offset(
            x = (size.width - squareSize) / 2,  // Centrage en X
            y = (size.height - squareSize) / 2 // Centrage en Y
        )




      /*  val screenWidth: Float = screenWidthOrigin * 1.25f
        val screenHeight = screenHeightOrigin * 1.25f


        val centerX = screenWidth / 2
        val centerY = screenHeight / 2
        val wallWidth = screenWidth / 2 // Largeur de base des murs
        val wallHeight = screenHeight / 2

        for (depth in 1..4) {
            val scale = 1f / depth
            val halfWall = (wallWidth * scale) / 2

            val left = centerX - halfWall
            val right = centerX + halfWall
            val top = centerY - (wallHeight * scale) / 2
            val bottom = centerY + (wallHeight * scale) / 2


            // Vérifier et dessiner les murs latéraux
            // Vérifier si un mur est devant
            val (wallX, wallY) = getForwardPosition(playerX, playerY, direction, depth)
            val (wallXLat, wallYLat) = getForwardPosition(playerX, playerY, direction, depth - 1)
            // On ne prend pas les bons murs latéraux
            val leftWallPos = getSidePosition(wallXLat, wallYLat, direction, -1) // Gauche
            val rightWallPos = getSidePosition(wallXLat, wallYLat, direction, 1)  // Droite

            if (isWall(leftWallPos)) {
                val texture = BitmapFactory.decodeResource(resources, R.drawable.brick)
                // drawTrapezoid(canvas, left, right, top, bottom, true, depth) // Mur gauche
                PerspectiveWallDrawer(canvas, texture).drawWallLeftPerspective( left, top, wallWidth/depth, wallHeight/depth, depth)
            }

            //}

            if (isWall(rightWallPos)) {
                val texture = BitmapFactory.decodeResource(resources, R.drawable.brick)
                PerspectiveWallDrawer(canvas, texture).drawWallRightPerspective(right, top, wallWidth/depth, wallHeight/depth, depth)

                //  drawTrapezoid(canvas, left, right, top, bottom, false, depth) // Mur droit
            }


            if (wallY in dungeonGrid.indices && wallX in dungeonGrid[0].indices && dungeonGrid[wallY][wallX] == 1) {
                val texture = BitmapFactory.decodeResource(resources, R.drawable.brick)
                val myShader = BitmapShader(texture, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR)

                val wallPaint = Paint().apply {
                    this.shader = myShader
                }


                canvas.drawRect(left, top, right, bottom, paintWall)

                //  drawWallToRight(canvas, wallX + 1,wallY, right, top, bottom, wallWidth/depth )
                //  canvas.drawRect(right, top, right + wallWidth /depth, bottom, paintWallRigt)
                break
            }


        }
        drawMunster(canvas)*/
    }

    private fun drawMunster(canvas: Canvas) {
        val texture = BitmapFactory.decodeResource(resources, R.drawable.goblin)
        val myShader = BitmapShader(texture, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR)

        //  if(mobsLeft == 0.0f) {
        val mobsLeft = ((width - texture.width) / 2).toFloat() + offsetX
        val mobsTop = ((height - texture.height) / 2).toFloat() + offsetY
        // }

        // Dessiner l'image sur le Canvas au centre
        canvas.drawBitmap(texture, mobsLeft.toFloat(), mobsTop.toFloat(), null)

        startShakingAnimation()
    }

    private fun drawWallToRight(canvas: Canvas,
                                wallX: Int,
                                wallY: Int,
                                posX: Float,
                                top: Float,
                                bottom: Float,
                                wallWidth: Float)
    {
        if(posX < width) {
            if (wallY in dungeonGrid.indices && wallX in dungeonGrid[0].indices && dungeonGrid[wallY][wallX] == 1) {
                canvas.drawRect(posX, top, posX + wallWidth, bottom, paintWallRigt)


            }
            drawWallToRight(canvas, wallX + 1, wallY, posX + wallWidth, top, bottom, wallWidth)
        }
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

    fun updateGrid(simpleGrid: Array<IntArray>, positionInSimpleGrid: Position) {
        dungeonGrid = simpleGrid
        playerX = positionInSimpleGrid.x
        playerY = positionInSimpleGrid.y
        direction = 0
        invalidate()
    }

    private var offsetX = 0f
    private var offsetY = 0f

    private fun startShakingAnimation() {
        val shakeAnimator = ValueAnimator.ofFloat(-10f, 10f).apply {
            duration = 50 // Oscillation rapide
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animation ->
                val shakeValue = animation.animatedValue as Float

                // Alterner les tremblements sur X et Y de manière aléatoire
                offsetX = (Math.random().toFloat() - 0.5f) * 20  // -10 à +10 pixels
                offsetY = (Math.random().toFloat() - 0.5f) * 20  // -10 à +10 pixels

                invalidate() // Redessiner l'image
            }
        }
        shakeAnimator.start()
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