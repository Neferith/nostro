package com.angelus.modulea.map

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.item.Inventory
import com.angelus.modulea.MapIds
import com.angelus.modulea.MapType

object Level1:AbstractMapProvider()  {

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
    override val mapId: MapIds = MapIds.LEVEL_1
    override val mapType: MapType = MapType.CAVERN
    override val doors: Map<Position, EntityPosition> = mapOf()
    override val inventories: Map<Position, Inventory> = mapOf()
}