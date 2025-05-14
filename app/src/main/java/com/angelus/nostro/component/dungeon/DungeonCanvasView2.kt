package com.angelus.nostro.component.dungeon

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Shader
import android.view.View
import androidx.compose.ui.geometry.Size
import com.angelus.gamedomain.entities.Position
import com.angelus.nostro.R

class DungeonCanvasView2(context: Context) : View(context) {

    // Use dependency injection
    private val cacheWallPaint: DungeonPaintCache = DungeonPaintCache(DungeonTextureProviderImpl(context))

    private var dungeonGrid: Array<IntArray> = emptyArray()
    private var mapType: String = ""

    private var playerX = 1
    private var playerY = 4
    private var direction = 0 // 0 = Nord, 1 = Est, 2 = Sud, 3 = Ouest

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = if(height> width)  width.toFloat() else height.toFloat()
        val height = if(height> width) width else height.toFloat()

        val squareWidth = Size(width, height)

        incrementSquare(canvas, squareWidth, 0.75f, deep = dungeonGrid.size - 1)
    }

    private fun incrementSquare(
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
            // TODO : FIX THIS le ratio devrait etre 0.5 pour les profondeurs suivantes. Mais ça bug.
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
                val leftTile = dungeonGrid[index][posX]
                if (leftTile >= 1) {

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
                 //   canvas.drawPath(path, paintWallLeft)

                    val wallPaint = cacheWallPaint.createLeftWallPaint(
                        mapType,
                        getTextureWallTypeByInt(leftTile),
                        leftDungeonSquare
                    )
                    // Dessiner le mur avec la texture
                    canvas.drawPath(path, wallPaint)

                    canvas.drawRect(
                        leftDungeonSquare.leftForward,
                        leftDungeonSquare.topForward,
                        leftDungeonSquare.rightFoward,
                        leftDungeonSquare.bottomForward,
                        cacheWallPaint.createFrontWallPaint(
                            mapType,
                            getTextureWallTypeByInt(leftTile),
                            leftDungeonSquare
                        )
                    )



                } else if (leftTile == 0) {
                    val floorPath = Path().apply {
                        moveTo(
                            leftDungeonSquare.leftBack,
                            leftDungeonSquare.bottomBack
                        ) // Début du path
                        lineTo(
                            leftDungeonSquare.rightBack,
                            leftDungeonSquare.bottomBack
                        ) // Ligne vers un point
                        lineTo(
                            leftDungeonSquare.rightFoward,
                            leftDungeonSquare.bottomForward
                        ) // Autre ligne
                        lineTo(
                            leftDungeonSquare.leftForward,
                            leftDungeonSquare.bottomForward
                        ) // Autre ligne
                        close() // Ferme la forme (revient au point initial)
                    }
                  //  canvas.drawPath(floorPath, paintWallLeft)

                    //val wallPaint =
                    canvas.drawPath(floorPath, cacheWallPaint.createFloorWallPaint(mapType,leftDungeonSquare))

                    val path = Path().apply {
                        moveTo(
                            leftDungeonSquare.leftBack,
                            leftDungeonSquare.topBack
                        ) // Début du path
                        lineTo(
                            leftDungeonSquare.rightBack,
                            leftDungeonSquare.topBack
                        ) // Ligne vers un point
                        lineTo(
                            leftDungeonSquare.rightFoward,
                            leftDungeonSquare.topForward
                        ) // Autre ligne
                        lineTo(
                            leftDungeonSquare.leftForward,
                            leftDungeonSquare.topForward
                        ) // Autre ligne
                        close() // Ferme la forme (revient au point initial)
                    }
                //    canvas.drawPath(path, paintWallLeft)

               //     val wallPaint = cacheWallPaint.createFloorWallPaint(resources,R.drawable.cell_floor,leftDungeonSquare)
                    canvas.drawPath(path, cacheWallPaint.createCeilingWallPaint(mapType,leftDungeonSquare))
                }

            }
        }

        var rightDungeonSquare = dungeonSquare
        for (posX in playerX +1..<dungeonGrid[index].size) {

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
                val rightTile = dungeonGrid[index][posX]
                if (rightTile >= 1) {

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

                    val wallPaint = cacheWallPaint.createRightWallPaint(
                        mapType,
                        getTextureWallTypeByInt(rightTile),
                        rightDungeonSquare
                    )
                    canvas.drawPath(path, wallPaint)

                    canvas.drawRect(
                        rightDungeonSquare.leftForward,
                        rightDungeonSquare.topForward,
                        rightDungeonSquare.rightFoward,
                        rightDungeonSquare.bottomForward,
                        cacheWallPaint.createFrontWallPaint(
                            mapType,
                            getTextureWallTypeByInt(rightTile),
                            rightDungeonSquare
                        )
                    )

                } else if (rightTile == 0) {
                    val floorPath = Path().apply {
                        moveTo(
                            rightDungeonSquare.leftBack,
                            rightDungeonSquare.bottomBack
                        ) // Début du path
                        lineTo(
                            rightDungeonSquare.rightBack,
                            rightDungeonSquare.bottomBack
                        ) // Ligne vers un point
                        lineTo(
                            rightDungeonSquare.rightFoward,
                            rightDungeonSquare.bottomForward
                        ) // Autre ligne
                        lineTo(
                            rightDungeonSquare.leftForward,
                            rightDungeonSquare.bottomForward
                        ) // Autre ligne
                        close() // Ferme la forme (revient au point initial)
                    }
                    //  canvas.drawPath(floorPath, paintWallLeft)

                    //val wallPaint =
                    canvas.drawPath(
                        floorPath,
                        cacheWallPaint.createFloorWallPaint(mapType,rightDungeonSquare)
                    )

                    val path = Path().apply {
                        moveTo(
                            rightDungeonSquare.leftBack,
                            rightDungeonSquare.topBack
                        ) // Début du path
                        lineTo(
                            rightDungeonSquare.rightBack,
                            rightDungeonSquare.topBack
                        ) // Ligne vers un point
                        lineTo(
                            rightDungeonSquare.rightFoward,
                            rightDungeonSquare.topForward
                        ) // Autre ligne
                        lineTo(
                            rightDungeonSquare.leftForward,
                            rightDungeonSquare.topForward
                        ) // Autre ligne
                        close() // Ferme la forme (revient au point initial)
                    }
                    //    canvas.drawPath(path, paintWallLeft)

                    //     val wallPaint = cacheWallPaint.createFloorWallPaint(resources,R.drawable.cell_floor,leftDungeonSquare)
                    canvas.drawPath(
                        path,
                        cacheWallPaint.createCeilingWallPaint(mapType,rightDungeonSquare)
                    )

                }
            }
        }

        // Dessin du sol
        val frontTile = dungeonGrid[index][playerX]
        if(frontTile >= 1) {
            canvas.drawRect(
                dungeonSquare.leftForward,
                dungeonSquare.topForward,
                dungeonSquare.rightFoward,
                dungeonSquare.bottomForward,
                cacheWallPaint.createFrontWallPaint(
                    mapType,
                    getTextureWallTypeByInt(frontTile),
                    dungeonSquare
                )
            )
        } else if(frontTile == 0) {

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
            canvas.drawPath(pathFloor, cacheWallPaint.createFloorWallPaint(mapType, dungeonSquare))

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
            canvas.drawPath(path, cacheWallPaint.createCeilingWallPaint(mapType, dungeonSquare))


        }
      //  drawMunster(canvas)

    }




    private fun drawMunster(canvas: Canvas) {
        val texture = BitmapFactory.decodeResource(resources, R.drawable.goblin)
        val myShader = BitmapShader(texture, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR)

        //  if(mobsLeft == 0.0f) {
        val mobsLeft = ((width - texture.width) / 2).toFloat() + offsetX
        val mobsTop = ((height - texture.height) / 2).toFloat() + offsetY
        // }

        // Dessiner l'image sur le Canvas au centre
        canvas.drawBitmap(texture, mobsLeft, mobsTop, null)

        startShakingAnimation()
    }

    fun updateGrid(mapType: String,
                   simpleGrid: Array<IntArray>,
                   positionInSimpleGrid: Position) {
        this.mapType = mapType
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