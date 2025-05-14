package com.angelus.nostro.component.dungeon

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.entities.TileType
import com.angelus.nostro.R

interface DungeonTextureProvider {
    enum class TextureType {
        WALL,
        FLOOR,
        CEILING,
        DOOR
    }
    fun getTexture(mapType: String, textureType: TextureType): Bitmap

    fun getWallType(wallType: String): TextureType


    //  fun getWallTypeByInt(wallTypeId: Int): TextureType
}

fun getTextureWallTypeByTile(tile: Tile): DungeonTextureProvider.TextureType {
    if(tile.type == TileType.STONE_DOOR) {
        return DungeonTextureProvider.TextureType.DOOR
    }
    return DungeonTextureProvider.TextureType.WALL
}

fun getTextureWallTypeByInt(wallTypeId: Int): DungeonTextureProvider.TextureType {
    if(wallTypeId == 2) {
        return DungeonTextureProvider.TextureType.DOOR
    }
    return DungeonTextureProvider.TextureType.WALL
}

class DungeonTextureProviderImpl(val context: Context): DungeonTextureProvider {

    override fun getWallType(wallType: String): DungeonTextureProvider.TextureType {
        if(wallType == "DOOR") {
            return DungeonTextureProvider.TextureType.DOOR
        }
        return DungeonTextureProvider.TextureType.WALL
    }




    var caches: MutableMap<Pair<String, DungeonTextureProvider.TextureType>, Bitmap> = mutableMapOf()

    override fun getTexture(mapType: String, textureType: DungeonTextureProvider.TextureType): Bitmap {
        val cache = caches.get(Pair(mapType, textureType))
        if(cache != null) {
            return cache
        } else {
            val texture = if(mapType.lowercase() == "cell") {
                getCellTexture(textureType)
            } else {
                getDefaultTexture(textureType)
            }
            if(caches.size > 15) {
                caches.clear()
            }
            caches.put(Pair(mapType, textureType), texture)

            return texture
        }
    }

    fun getCellTexture(textureType: DungeonTextureProvider.TextureType): Bitmap {

        return when(textureType) {
            DungeonTextureProvider.TextureType.WALL -> BitmapFactory.decodeResource(context.resources,
                R.drawable.cell_wall_low)
            DungeonTextureProvider.TextureType.FLOOR -> BitmapFactory.decodeResource(context.resources,
                R.drawable.cell_floor_low)
            DungeonTextureProvider.TextureType.CEILING -> BitmapFactory.decodeResource(context.resources,
                R.drawable.cell_ceiling_low)
            DungeonTextureProvider.TextureType.DOOR -> BitmapFactory.decodeResource(context.resources,
                R.drawable.draft_door_closed)
        }

    }

    fun getDefaultTexture(textureType: DungeonTextureProvider.TextureType): Bitmap {

        return when(textureType) {
            DungeonTextureProvider.TextureType.WALL -> BitmapFactory.decodeResource(context.resources,
                R.drawable.cavern_wall_low)
            DungeonTextureProvider.TextureType.FLOOR -> BitmapFactory.decodeResource(context.resources,
                R.drawable.cavern_wall_low)
            DungeonTextureProvider.TextureType.CEILING -> BitmapFactory.decodeResource(context.resources,
                R.drawable.cavern_wall_low)
            DungeonTextureProvider.TextureType.DOOR -> BitmapFactory.decodeResource(context.resources,
                R.drawable.draft_door_closed)
        }

    }

}