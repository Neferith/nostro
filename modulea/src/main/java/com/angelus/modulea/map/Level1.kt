package com.angelus.modulea.map

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Position

class Level1:AbstractMapProvider()  {

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
    override val doors: Map<Position, EntityPosition> = mapOf()
}