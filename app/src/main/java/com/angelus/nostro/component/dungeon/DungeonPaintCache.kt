package com.angelus.nostro.component.dungeon

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Shader
import android.util.Log

class DungeonPaintCache {
    private val cache = mutableMapOf<String, Paint>()

    fun createFrontWallPaint(resources: Resources, textureId: Int, width: Int, height: Int): Paint {
        val key =
            textureId.toString() + width.toString() + height.toString()//Triple(textureId, width, height)

        return cache[key] ?: run {
            val texture = BitmapFactory.decodeResource(resources, textureId)
            val scaledTexture = Bitmap.createScaledBitmap(texture, width, height, true)

            val shader = BitmapShader(scaledTexture, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            val paint = Paint().apply { this.shader = shader }

            cache[key] = paint
            paint
        }
    }

    fun createLeftWallPaint(
        resources: Resources, textureId: Int,
        wallPoints: DungeonSquare
    ): Paint {
        val key = "LEFT_" + textureId.toString() + wallPoints.hashCode()


        return cache[key] ?: run {
            val texture = BitmapFactory.decodeResource(resources, textureId)

            val matrix = Matrix()
            // Points source (rect normal)
            val src = floatArrayOf(
                0f, 0f,                   // Haut gauche de la texture
                texture.width.toFloat(), 0f,  // Haut droit de la texture
                texture.width.toFloat(), texture.height.toFloat(),  // Bas droit
                0f, texture.height.toFloat() // Bas gauche
            )
            val dst = floatArrayOf(
                wallPoints.rightFoward, wallPoints.topForward,  // Haut avant droit
                wallPoints.rightBack, wallPoints.topBack,      // Haut arrière droit
                wallPoints.rightBack, wallPoints.bottomBack,   // Bas arrière droit
                wallPoints.rightFoward, wallPoints.bottomForward // Bas avant droit
            )

            // Appliquer la transformation
            val success = matrix.setPolyToPoly(src, 0, dst, 0, 4)
            if (!success) {
                Log.e("ERROR", "Échec de la transformation de la texture")
            }

            val shader = BitmapShader(texture, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            shader.setLocalMatrix(matrix)

            val wallPaint = Paint().apply {
                this.shader = shader
            }
            cache[key] = wallPaint
            return wallPaint
            //  return shader
        }
    }

    fun createRightWallPaint(
        resources: Resources, textureId: Int,
        wallPoints: DungeonSquare
    ): Paint {
        val key = "RIGHT_" + textureId.toString() + wallPoints.hashCode()


        return cache[key] ?: run {
            val texture = BitmapFactory.decodeResource(resources, textureId)

            val matrix = Matrix()

            // Points source (rect normal)
            // Points source : Un rectangle standard (0,0 → width, height)
            val src = floatArrayOf(
                0f, 0f,                   // Haut gauche de la texture
                texture.width.toFloat(), 0f,  // Haut droit de la texture
                texture.width.toFloat(), texture.height.toFloat(),  // Bas droit
                0f, texture.height.toFloat() // Bas gauche
            )

// Points de destination : Ceux du mur droit
            val dst = floatArrayOf(
                wallPoints.leftBack, wallPoints.topBack,  // Haut avant gauche
                wallPoints.leftForward, wallPoints.topForward,        // Haut arrière gauche
                wallPoints.leftForward, wallPoints.bottomForward,     // Bas arrière gauche
                wallPoints.leftBack, wallPoints.bottomBack // Bas avant gauche
            )

            // Appliquer la transformation
            //  matrix.setScale(scaleX, scaleY)
            val success = matrix.setPolyToPoly(src, 0, dst, 0, 4)
            if (!success) {
                Log.e("ERROR", "Échec de la transformation de la texture")
            }

            val shader = BitmapShader(texture, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            shader.setLocalMatrix(matrix)

            val wallPaint = Paint().apply {
                this.shader = shader
            }
            cache[key] = wallPaint
            return wallPaint
        }
    }
}