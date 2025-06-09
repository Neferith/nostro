package com.angelus.nostro.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.angelus.dungeonengine.DungeonTextureProvider
import com.angelus.nostro.R

class DungeonTextureProviderImpl(val context: Context): DungeonTextureProvider {

    override fun getWallType(wallType: String): DungeonTextureProvider.TextureType {
        if(wallType == "DOOR") {
            return DungeonTextureProvider.TextureType.DOOR
        }
        return DungeonTextureProvider.TextureType.WALL
    }

    override fun getItemBitmap(itemId: String): Bitmap {
        return BitmapFactory.decodeResource(context.resources, R.drawable.bag_low)
    }

    override fun getNPCBitmap(itemId: String): Bitmap {
        return BitmapFactory.decodeResource(context.resources, R.drawable.goblin)
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