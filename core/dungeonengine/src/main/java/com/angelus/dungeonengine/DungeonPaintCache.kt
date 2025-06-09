package com.angelus.dungeonengine

import android.graphics.BitmapShader
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Shader
import android.util.Log

class DungeonPaintCache(
    private val provider: DungeonTextureProvider
) {
    private val cache = mutableMapOf<String, Paint>()

    fun createFrontWallPaint(
        mapType: String,
        wallType: DungeonTextureProvider.TextureType = DungeonTextureProvider.TextureType.WALL,
        wallPoints: DungeonSquare
    ): Paint {
        val key = wallType.toString() + "_" + mapType + wallPoints.hashCode()

        return cache[key] ?: run {
            val texture = provider.getTexture(
                mapType,
                wallType
            )

            val matrix = Matrix()
            val src = floatArrayOf(
                0f, 0f,                   // Haut gauche de la texture
                texture.width.toFloat(), 0f,  // Haut droit de la texture
                texture.width.toFloat(), texture.height.toFloat(),  // Bas droit
                0f, texture.height.toFloat() // Bas gauche
            )
            val dst = floatArrayOf(
                wallPoints.leftForward, wallPoints.topForward,  // Haut avant droit
                wallPoints.rightFoward, wallPoints.topForward,      // Haut arrière droit
                wallPoints.rightFoward, wallPoints.bottomForward,   // Bas arrière droit
                wallPoints.leftForward, wallPoints.bottomForward // Bas avant droit
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
        }
    }

    fun createFloorWallPaint(mapType: String, wallPoints: DungeonSquare): Paint {
        val key = "FLOOR_" + mapType + wallPoints.hashCode()

        return cache[key] ?: run {
            val texture = provider.getTexture(
                mapType,
                DungeonTextureProvider.TextureType.FLOOR
            )

            val matrix = Matrix()
            // Points source (rect normal)
            val src = floatArrayOf(
                0f, 0f,                   // Haut gauche de la texture
                texture.width.toFloat(), 0f,  // Haut droit de la texture
                texture.width.toFloat(), texture.height.toFloat(),  // Bas droit
                0f, texture.height.toFloat() // Bas gauche
            )
            val dst = floatArrayOf(
                wallPoints.leftBack, wallPoints.bottomBack,  // Haut avant droit
                wallPoints.rightBack, wallPoints.bottomBack,      // Haut arrière droit
                wallPoints.rightFoward, wallPoints.bottomForward,   // Bas arrière droit
                wallPoints.leftForward, wallPoints.bottomForward // Bas avant droit
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
        }
    }

    fun createCeilingWallPaint(mapType: String, wallPoints: DungeonSquare): Paint {
        val key = "CEILING_" + mapType + wallPoints.hashCode()

        return cache[key] ?: run {
            val texture = provider.getTexture(
                mapType,
                DungeonTextureProvider.TextureType.CEILING
            )

            val matrix = Matrix()
            // Points source (rect normal)
            val src = floatArrayOf(
                0f, 0f,                   // Haut gauche de la texture
                texture.width.toFloat(), 0f,  // Haut droit de la texture
                texture.width.toFloat(), texture.height.toFloat(),  // Bas droit
                0f, texture.height.toFloat() // Bas gauche
            )
            val dst = floatArrayOf(
                wallPoints.leftBack, wallPoints.topBack,  // Haut avant droit
                wallPoints.rightBack, wallPoints.topBack,      // Haut arrière droit
                wallPoints.rightFoward, wallPoints.topForward,   // Bas arrière droit
                wallPoints.leftForward, wallPoints.topForward // Bas avant droit
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
        }
    }

    fun createLeftWallPaint(
        mapType: String,
        wallType: DungeonTextureProvider.TextureType = DungeonTextureProvider.TextureType.WALL,
        wallPoints: DungeonSquare
    ): Paint {
        val key = wallType.toString() + "_LEFT_" + mapType + wallPoints.hashCode()
        return cache[key] ?: run {
            val texture = provider.getTexture(
                mapType,
                wallType
            )

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
        }
    }

    fun createRightWallPaint(
        mapType: String,
        wallType: DungeonTextureProvider.TextureType = DungeonTextureProvider.TextureType.WALL,
        wallPoints: DungeonSquare
    ): Paint {
        val key = wallType.toString()+"_RIGHT_" + mapType + wallPoints.hashCode()


        return cache[key] ?: run {
            val texture = provider.getTexture(
                mapType,
                wallType
            )

            val matrix = Matrix()

            // Points source (rect normal)
            // Points source : Un rectangle standard (0,0 → width, height)
            val src = floatArrayOf(
                0f, 0f,                   // Haut gauche de la texture
                texture.width.toFloat(), 0f,  // Haut droit de la texture
                texture.width.toFloat(), texture.height.toFloat(),  // Bas droit
                0f, texture.height.toFloat() // Bas gauche
            )

            val dst = floatArrayOf(
                wallPoints.leftBack, wallPoints.topBack,  // Haut avant gauche
                wallPoints.leftForward, wallPoints.topForward,        // Haut arrière gauche
                wallPoints.leftForward, wallPoints.bottomForward,     // Bas arrière gauche
                wallPoints.leftBack, wallPoints.bottomBack // Bas avant gauche
            )

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