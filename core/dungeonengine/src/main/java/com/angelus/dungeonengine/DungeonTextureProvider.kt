package com.angelus.dungeonengine

import android.graphics.Bitmap
import com.angelus.dungeonengine.model.TileUiState
import com.angelus.mapdomain.entities.TileType

interface DungeonTextureProvider {
    enum class TextureType {
        WALL,
        FLOOR,
        CEILING,
        DOOR
    }
    fun getTexture(mapType: String, textureType: TextureType): Bitmap

    fun getWallType(wallType: String): TextureType

    fun getItemBitmap(itemId: String): Bitmap

    fun getNPCBitmap(itemId: String): Bitmap


    //  fun getWallTypeByInt(wallTypeId: Int): TextureType
}

fun getTextureWallTypeByTile(tile: TileUiState): DungeonTextureProvider.TextureType {
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