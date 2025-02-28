package com.angelus.nostro.component

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.graphics.SurfaceTexture
import android.util.Log
import android.view.TextureView
import android.view.View
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.angelus.gamedomain.entities.Position
import com.angelus.nostro.R
import com.angelus.nostro.utils.PerspectiveWallDrawer
import com.angelus.nostro.utils.WallPoint
import com.angelus.nostro.utils.drawPerspectiveWallShape
import kotlin.math.min


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

        val width = if(height> width)  width.toFloat() else height.toFloat()
        val height = if(height> width)  width.toFloat() else height.toFloat()//height.toFloat()

        // Dessin du sol
        canvas.drawRect(0f, height / 2, width, height, paintFloor)

        // Dessiner la perspective des murs
        //newDrawDungeonPerspective(canvas, width, height, 1)
        drawDungeonPerspective(canvas,width,height)
       // newDrawDungeonPerspectiveBis(canvas, width, height, Position(playerX, playerY),1)
    }

    val DEPTH_MAX = 4

    private  fun newDrawDungeonPerspectiveBis(canvas: Canvas,
                                           screenWidth: Float,
                                           screenHeight: Float,
        position: Position,
                                           depth:Int) {
        val centerX = screenWidth / 2
        val centerY = screenHeight / 2
        val wallWidth = screenWidth / 2 // Largeur de base des murs
        val wallHeight = screenHeight / 2

        val (wallForwardX, wallForwardY) = getForwardPosition(playerX, playerY, direction, depth)
        val (wallXLat, wallYLat) = getForwardPosition(playerX, playerY, direction, depth - 1)

        val scale = 1f / depth
        val halfWall = (wallWidth * scale) / 2

        val left = centerX - halfWall
        val right = centerX + halfWall
        val top = centerY - (wallHeight * scale) / 2
        val bottom = centerY + (wallHeight * scale) / 2


        val texture = BitmapFactory.decodeResource(resources, R.drawable.brick)
        var maxX = dungeonGrid[0].size
        var minX = 0

        if (position.y in dungeonGrid.indices) {
            var wallRightIndex = 0
            for(rightX in position.x +1 .. dungeonGrid[position.y].size) {

                if (rightX in dungeonGrid[0].indices && dungeonGrid[position.y][rightX] == 1) {
                    val wallX = right +  (wallRightIndex * wallWidth)
                    PerspectiveWallDrawer(canvas, texture).drawWallRightPerspective(wallX, top,
                        wallWidth/depth,
                        wallHeight/depth, depth)
                    break

                }
                wallRightIndex ++
            }
            maxX = position.x + wallRightIndex;


            for(rightY in position.y + 1 .. dungeonGrid.size) {

            }


            var wallLeftIndex = 0

            for(rightX in position.x -1 .. 0) {

                if (rightX in dungeonGrid[0].indices && dungeonGrid[position.y][rightX] == 1) {
                    val wallX = left -  (wallLeftIndex * wallWidth)
                    PerspectiveWallDrawer(canvas, texture).drawWallLeftPerspective(wallX, top,
                        wallWidth/depth,
                        wallHeight/depth, depth)
                    break

                }
                wallLeftIndex ++
            }
            minX = position.x - wallLeftIndex;

            for(wallForwardX in minX  .. maxX ) {

                if (position.y - 1 in dungeonGrid.indices && wallForwardX in dungeonGrid[0].indices && dungeonGrid[position.y - 1][wallForwardX] == 1) {


                    val texture = BitmapFactory.decodeResource(resources, R.drawable.brick)
                    val myShader =
                        BitmapShader(texture, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR)

                    val wallPaint = Paint().apply {
                        this.shader = myShader
                    }

                    val cursor = (wallForwardX - position.x) * wallWidth

                    canvas.drawRect(left + cursor, top, right + cursor, bottom, paintWall)
                }

            }



           /* val rightPoint: Position = Position(position.x +1, position.y)
            if (rightPoint.x in dungeonGrid[0].indices && dungeonGrid[position.x][position.y] == 1) {
                PerspectiveWallDrawer(canvas, texture).drawWallRightPerspective(right, top,
                    wallWidth/depth,
                    wallHeight/depth, depth)

            }*/
        }


      //  if (wallY in dungeonGrid.indices && wallX in dungeonGrid[0].indices && dungeonGrid[wallY][wallX] == 1) {

        //}

        //for (depth in 1..4) {

        //}
    }

    private  fun newDrawDungeonPerspective(canvas: Canvas,
                                           screenWidth: Float,
                                           screenHeight: Float,
                                           //x: Int,
                                           //y: Int,
                                            depth:Int) {
        val centerX = screenWidth / 2
        val centerY = screenHeight / 2
        val wallWidth = screenWidth / 2 // Largeur de base des murs
        val wallHeight = screenHeight / 2

        val (wallForwardX, wallForwardY) = getForwardPosition(playerX, playerY, direction, depth)
        val (wallXLat, wallYLat) = getForwardPosition(playerX, playerY, direction, depth - 1)

        val scale = 1f / depth
        val halfWall = (wallWidth * scale) / 2

        val left = centerX - halfWall
        val right = centerX + halfWall
        val top = centerY - (wallHeight * scale) / 2
        val bottom = centerY + (wallHeight * scale) / 2

       /* val rightWallPos = getSidePosition(wallXLat, wallYLat, direction, 1)  // Droite
        if (isWall(rightWallPos)) {
            val texture = BitmapFactory.decodeResource(resources, R.drawable.brick)
            PerspectiveWallDrawer(canvas, texture).drawWallRightPerspective(right, top, wallWidth/depth, wallHeight/depth, depth)
        } else {
            drawWallToRight(canvas,wallForwardX, wallForwardY, right, top, bottom, wallWidth)
        }*/
        for (depth in 4 downTo 1) {

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


            if (wallY in dungeonGrid.indices && wallX in dungeonGrid[0].indices && dungeonGrid[wallY][wallX] == 1) {
                val texture = BitmapFactory.decodeResource(resources, R.drawable.brick)
                val myShader = BitmapShader(texture, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR)

                val wallPaint = Paint().apply {
                    this.shader = myShader
                }


                canvas.drawRect(left, top, right, bottom, paintWall)


                //  canvas.drawRect(right, top, right + wallWidth /depth, bottom, paintWallRigt)
               // break
            }
            drawWallToRight(canvas, wallX + 1,wallY, right, top, bottom, wallWidth/depth )
        }


    }

    private fun drawDungeonPerspective(canvas: Canvas, screenWidth: Float, screenHeight: Float) {
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
        drawMunster(canvas)
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