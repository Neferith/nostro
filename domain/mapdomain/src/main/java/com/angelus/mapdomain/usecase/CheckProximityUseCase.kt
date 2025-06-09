package com.angelus.mapdomain.usecase

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Orientation
import com.angelus.gamedomain.entities.Position
import com.angelus.mapdomain.entities.GameMap
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.repository.CurrentMapRepository

interface CheckProximityUseCase {
    suspend operator fun invoke(source: EntityPosition, target: EntityPosition, distance: Int): Position?
}

