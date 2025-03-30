package com.angelus.nostro.component.dungeon

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.Log
import android.view.View
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.Offset
import com.angelus.gamedomain.entities.Position
import com.angelus.nostro.R
import com.angelus.nostro.utils.WallPoints
import com.angelus.nostro.utils.drawPerspectiveWallShape



class DungeonCanvasView2(context: Context) : View(context) {

    private val cacheWallPaint: DungeonPaintCache = DungeonPaintCache()

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

        var leftDungeonSquare = dungeonSquare
        for (posX in playerX -1 downTo 0) {

            leftDungeonSquare = DungeonSquare(
                leftBack = leftDungeonSquare.leftBack - newSquareWidth.width,
                topBack = leftDungeonSquare.topBack,
                rightBack = leftDungeonSquare.rightBack - newSquareWidth.width,
                bottomBack = leftDungeonSquare.bottomBack,
                leftForward = leftDungeonSquare.leftForward - oldSquareWidth.width,
                topForward = leftDungeonSquare.topForward,
                rightFoward = leftDungeonSquare.rightFoward - oldSquareWidth.width,
                bottomForward = leftDungeonSquare.bottomForward
            )


            if (leftDungeonSquare.rightBack > 0) {
                if (dungeonGrid[index][posX] == 1) {

                    val path = Path().apply {
                        moveTo(
                            leftDungeonSquare.rightFoward,
                            leftDungeonSquare.topForward
                        ) // Début du path
                        lineTo(
                            leftDungeonSquare.rightBack,
                            leftDungeonSquare.topBack
                        ) // Ligne vers un point
                        lineTo(
                            leftDungeonSquare.rightBack,
                            leftDungeonSquare.bottomBack
                        ) // Autre ligne
                        lineTo(
                            leftDungeonSquare.rightFoward,
                            leftDungeonSquare.bottomForward
                        ) // Autre ligne
                        close() // Ferme la forme (revient au point initial)
                    }
                    canvas.drawPath(path, paintWallLeft)

                    val wallPaint = cacheWallPaint.createLeftWallPaint(resources,R.drawable.cell_wall,leftDungeonSquare)
                    // Dessiner le mur avec la texture
                    canvas.drawPath(path, wallPaint)

                    canvas.drawRect(
                        leftDungeonSquare.leftForward,
                        leftDungeonSquare.topForward,
                        leftDungeonSquare.rightFoward,
                        leftDungeonSquare.bottomForward,
                        cacheWallPaint.createFrontWallPaint(resources, R.drawable.cell_wall,newSquareWidth.width.toInt(), newSquareWidth.height.toInt())//getWallpaint(newSquareWidth.width.toInt(), newSquareWidth.height.toInt())
                    )



                } else {
                    //   canvas.drawRect(left, top, right, bottom, paintFloor)
                }

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
            if (leftDungeonSquare.leftBack < width) {
                if (dungeonGrid[index][posX] == 1) {

                    val path = Path().apply {
                        moveTo(
                            rightDungeonSquare.leftForward,
                            rightDungeonSquare.topForward
                        ) // Début du path
                        lineTo(
                            rightDungeonSquare.leftBack,
                            rightDungeonSquare.topBack
                        ) // Ligne vers un point
                        lineTo(
                            rightDungeonSquare.leftBack,
                            rightDungeonSquare.bottomBack
                        ) // Autre ligne
                        lineTo(
                            rightDungeonSquare.leftForward,
                            rightDungeonSquare.bottomForward
                        ) // Autre ligne
                        close() // Ferme la forme (revient au point initial)
                    }

                    val wallPaint = cacheWallPaint.createRightWallPaint(resources,R.drawable.cell_wall,rightDungeonSquare)
                    canvas.drawPath(path, wallPaint)

                    canvas.drawRect(
                        rightDungeonSquare.leftForward,
                        rightDungeonSquare.topForward,
                        rightDungeonSquare.rightFoward,
                        rightDungeonSquare.bottomForward,
                        cacheWallPaint.createFrontWallPaint(
                            resources, R.drawable.cell_wall,newSquareWidth.width.toInt(), newSquareWidth.height.toInt())//getWallpaint(newSquareWidth.width.toInt(), newSquareWidth.height.toInt())
                    )

                }
            }
        }

        // Dessin du sol
        if(dungeonGrid[index][playerX] == 1) {
            canvas.drawRect(
                dungeonSquare.leftForward,
                dungeonSquare.topForward,
                dungeonSquare.rightFoward,
                dungeonSquare.bottomForward,
                cacheWallPaint.createFrontWallPaint(
                    resources,
                    R.drawable.cell_wall,newSquareWidth.width.toInt(),
                    newSquareWidth.height.toInt()
                )
            )
        } else if(dungeonGrid[index][playerX] == 0) {

            val pathFloor = Path().apply {
                moveTo(
                    dungeonSquare.leftForward,
                    dungeonSquare.bottomForward
                ) // Début du path
                lineTo(
                    dungeonSquare.rightFoward,
                    dungeonSquare.bottomForward
                ) // Ligne vers un point
                lineTo(
                    dungeonSquare.rightBack,
                    dungeonSquare.bottomBack
                ) // Autre ligne
                lineTo(
                    dungeonSquare.leftBack,
                    dungeonSquare.bottomBack
                ) // Autre ligne
                close() // Ferme la forme (revient au point initial)
            }
            canvas.drawPath(pathFloor, paintWallRigt)

            val path = Path().apply {
                moveTo(
                    dungeonSquare.leftForward,
                    dungeonSquare.topForward
                ) // Début du path
                lineTo(
                    dungeonSquare.rightFoward,
                    dungeonSquare.topForward
                ) // Ligne vers un point
                lineTo(
                    dungeonSquare.rightBack,
                    dungeonSquare.topBack
                ) // Autre ligne
                lineTo(
                    dungeonSquare.leftBack,
                    dungeonSquare.topBack
                ) // Autre ligne
                close() // Ferme la forme (revient au point initial)
            }
            canvas.drawPath(path, paintWallLeft)
           // canvas.drawRect(left, top, right, bottom, paintFloor)
        }
       // drawMunster(canvas)

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
}