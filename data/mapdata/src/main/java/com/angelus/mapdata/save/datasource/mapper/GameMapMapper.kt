package com.angelus.mapdata.save.datasource.mapper

import com.angelus.gamedata.data.mapper.convertFromDTO
import com.angelus.gamedata.data.mapper.convertToDTO
import com.angelus.mapdata.save.datasource.dto.GameMapDTO
import com.angelus.mapdata.save.datasource.dto.TileDTO
import com.angelus.mapdomain.entities.GameMap
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.entities.TileType

fun GameMap.convertToDTO(): GameMapDTO {
    return GameMapDTO(
        id = this.id,
        mapType = this.mapType,
        size = this.size.convertToDTO(),
        defaultTileType = this.defaultTileType.toSerializedString(),
        tiles = this.getCustomTiles().map { (key, values) -> key.convertToDTO() to values.convertToDTO() }.toMap()
    )
}

fun GameMapDTO.convertFromDTO(): GameMap {
    return GameMap(
        id = this.id,
        mapType = this.mapType,
        size = this.size.convertFromDTO(),
        defaultTileType = this.defaultTileType.toTileTypeOrDefault(TileType.STONE_WALL),
        tiles = this.tiles.map {
            (key, values) -> key.convertFromDTO() to values.convertFromDTO()
        }.toMap().toMutableMap()
    )
}



fun Tile.convertToDTO(): TileDTO {
    return TileDTO(
        type = this.type.toSerializedString(),
        walkable = this.walkable,
        transition = this.transition?.convertToDTO(),
        inventory = this.inventory?.convertToDTO()
    )
}

fun TileDTO.convertFromDTO(): Tile {
    return Tile(
        type = this.type.toTileTypeOrDefault(TileType.STONE_WALL),
        walkable = this.walkable,
        transition = this.transition?.convertFromDTO(),
        inventory = this.inventory?.convertFromDTO()
    )
}

// Extension sur TileType
fun TileType.toSerializedString(): String {
    return name
}

// Extension sur String
fun String.toTileTypeOrNull(): TileType? {
    return TileType.entries.firstOrNull { it.name == this }
}

// Variante avec une valeur par défaut si tu veux éviter les nulls
fun String.toTileTypeOrDefault(default: TileType = TileType.STONE_FLOOR): TileType {
    return TileType.entries.firstOrNull { it.name == this } ?: default
}