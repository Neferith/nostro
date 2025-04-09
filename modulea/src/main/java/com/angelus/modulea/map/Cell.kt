package com.angelus.modulea.map

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Orientation
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.item.Inventory
import com.angelus.modulea.MapIds
import com.angelus.modulea.MapType
import com.angelus.modulea.item.NostroCross

object Cell : AbstractMapProvider() {
    override val dungeonGrid = arrayOf(
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    )
    override val mapId: MapIds = MapIds.CELL
    override val mapType: MapType = MapType.CELL
    override val doors: Map<Position, EntityPosition> = mapOf(
        Position(2, 10) to EntityPosition(
            MapIds.LEVEL_3.toString(),
            Position(5, 3),
            Orientation.SOUTH
        )

    )
    override val inventories: Map<Position, Inventory> = mapOf(
        Position(3, 3) to Inventory(
            mapOf(
                NostroCross.id to 1
            )
        )
    )


    /*val gameMap: GameMap by lazy {
       val gameMap = GameMap("", Size(dungeonGrid.get(0).size, dungeonGrid.size), TileType.STONE_WALL)
       for (y in 0 .. dungeonGrid.size -1) {
           for (x in 0 .. dungeonGrid[y].size -1) {
               if(dungeonGrid[y][x] == 0) {
                   gameMap.setTileAt(
                       Position(x,y),
                       Tile(TileType.STONE_FLOOR)
                   )
               }
           }
       }
       gameMap
   }*/
}