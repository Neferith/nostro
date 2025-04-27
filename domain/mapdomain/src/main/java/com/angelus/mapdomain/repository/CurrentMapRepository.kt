package com.angelus.mapdomain.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.mapdomain.entities.Panorama
import com.angelus.mapdomain.entities.Tile

sealed class MoveType {
    object blocked: MoveType()
    object walkable: MoveType()
    data class transition(val position: EntityPosition): MoveType()
}

interface CurrentMapRepository {
    fun getPanorama(entityPosition: EntityPosition): Panorama?
    fun checkMoveInMap(
        entityPosition: EntityPosition,
        moveDistance: Int,
        direction: Direction
    ): MoveType

    fun getTileAtPosition(entityPosition: EntityPosition): Result<Tile>
}