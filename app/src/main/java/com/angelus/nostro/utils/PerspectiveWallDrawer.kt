package com.angelus.nostro.utils

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.Log

data class WallPoint(val x: Float, val y: Float)

data class WallPoints(
    val top: WallPoint,
    val bottom: WallPoint,
    val bottomDepth: WallPoint,
    val topDepth: WallPoint
)

class PerspectiveWallDrawer(val canvas: Canvas, val texture: Bitmap) {
    fun drawWallLeftPerspective(
        startX: Float,
        startY: Float,
        wallWidth: Float,
        wallHeight: Float,
        depth: Int
    ) {
        // Créer les points du mur
        val wallPoints = createLeftPerspectiveWallPoints(startX, startY, wallWidth, wallHeight)

        drawPerspective(wallPoints)
    }

    fun drawWallRightPerspective(
        // canvas: Canvas,
        //texture: Bitmap,
        startX: Float,
        startY: Float,
        wallWidth: Float,
        wallHeight: Float,
        depth: Int
    ) {
        // Créer les points du mur
        val wallPoints = createRightPerspectiveWallPoints(startX, startY, wallWidth, wallHeight)

        drawPerspective(wallPoints)
    }

    private  fun drawPerspective(wallPoints: WallPoints) {
        // Créer le chemin du mur
        val path = createWallPath(wallPoints)

        // Créer le shader de la texture
        val shader = createTextureShader(texture, wallPoints)

        // Appliquer le shader
        val wallPaint = Paint().apply {
            this.shader = shader
        }

        // Dessiner le mur avec la texture
        canvas.drawPath(path, wallPaint)

    }

    private fun createLeftPerspectiveWallPoints(
        startX: Float,
        startY: Float,
        wallWidth: Float,
        wallHeight: Float
    ): WallPoints {
        val topPoint = WallPoint(startX, startY)
        val bottomPoint = WallPoint(topPoint.x, topPoint.y + wallHeight)
        val bottomDepthPoint = WallPoint(bottomPoint.x - wallWidth / 2, bottomPoint.y + wallWidth / 2)
        val topDepthPoint = WallPoint(bottomDepthPoint.x, topPoint.y - wallHeight / 2)

        return WallPoints(topPoint, bottomPoint, bottomDepthPoint, topDepthPoint)
    }

    private fun createRightPerspectiveWallPoints(
        startX: Float,
        startY: Float,
        wallWidth: Float,
        wallHeight: Float
    ): WallPoints {
        val topPoint = WallPoint(startX, startY)
        val bottomPoint = WallPoint(topPoint.x, topPoint.y + wallHeight)
        val bottomDepthPoint = WallPoint(bottomPoint.x + wallWidth / 2, bottomPoint.y + wallWidth / 2)
        val topDepthPoint = WallPoint(bottomDepthPoint.x, topPoint.y - wallHeight / 2)

        return WallPoints(topPoint, bottomPoint, bottomDepthPoint, topDepthPoint)
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

    private fun createTextureShader(
        texture: Bitmap,
        wallPoints: WallPoints
    ): Shader {
        val matrix = Matrix()
        val srcPoints = floatArrayOf(
            0f, 0f,
            texture.width.toFloat(), 0f,
            texture.width.toFloat(), texture.height.toFloat(),
            0f, texture.height.toFloat()
        )

        val dstPoints = floatArrayOf(
            wallPoints.topDepth.x, wallPoints.topDepth.y,
            wallPoints.top.x, wallPoints.top.y,
            wallPoints.bottom.x, wallPoints.bottom.y,
            wallPoints.bottomDepth.x, wallPoints.bottomDepth.y
        )

        // Appliquer la transformation
        val success = matrix.setPolyToPoly(srcPoints, 0, dstPoints, 0, 4)
        if (!success) {
            Log.e("ERROR", "Échec de la transformation de la texture")
        }

        val shader = BitmapShader(texture, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        shader.setLocalMatrix(matrix)
        return shader
    }
}