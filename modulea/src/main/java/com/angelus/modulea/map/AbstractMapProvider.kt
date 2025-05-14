package com.angelus.modulea.map

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.Size
import com.angelus.gamedomain.entities.item.Inventory
import com.angelus.mapdomain.entities.GameMap
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.entities.TileType
import com.angelus.modulea.MapIds
import com.angelus.modulea.MapType

abstract class AbstractMapProvider {
    abstract val dungeonGrid: Array<IntArray>
    abstract val mapId: MapIds
    abstract val mapType: MapType

    abstract val doors: Map<Position, EntityPosition>
    abstract val inventories: Map<Position, Inventory>

    val gameMap: GameMap by lazy {
        val gameMap = GameMap(
            mapId.toString(),
            mapType.toString(),
            Size(dungeonGrid.get(0).size, dungeonGrid.size), TileType.STONE_WALL)
        for (y in 0 .. dungeonGrid.size -1) {
            for (x in 0 .. dungeonGrid[y].size -1) {
                if(dungeonGrid[y][x] == 0) {
                    gameMap.setTileAt(
                        Position(x,y),
                        Tile(type = TileType.STONE_FLOOR, inventory =  inventories.get(Position(x,y))),

                    )
                }
                if(dungeonGrid[y][x] == 2) {
                    val doorTransition =  doors.get(Position(x,y))
                    gameMap.setTileAt(
                        Position(x,y),
                        Tile(TileType.STONE_DOOR,
                            if(doorTransition != null) true else false,
                           doorTransition)
                    )
                }
            }
        }
        gameMap
    }
}