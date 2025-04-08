package com.angelus.gamedata.data.mapper

import com.angelus.gamedata.data.dto.EntityPositionDTO
import com.angelus.gamedata.data.dto.PositionDTO
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Orientation
import com.angelus.gamedomain.entities.Position

fun Position.convertToDTO(): PositionDTO {
    return PositionDTO(
        x = this.x,
        y = this.y
    )
}

fun PositionDTO.convertFromDTO(): Position {
    return Position(
        x = this.x,
        y = this.y
    )
}

fun EntityPosition.convertCharacterToDTO(): EntityPositionDTO {
    return EntityPositionDTO(
        mapId = this.mapId,
        position = this.position.convertToDTO(),
        orientation = this.orientation.toString()
    )
}

fun EntityPositionDTO.convertCharacterFromDTO(): EntityPosition {
    return EntityPosition(
        mapId = this.mapId,
        position = this.position.convertFromDTO(),
        orientation = this.orientation.toOrientation()
    )
}

fun String.toOrientation(): Orientation {
    return try {
        Orientation.valueOf(this.uppercase())
    } catch (e: IllegalArgumentException) {
        return Orientation.NORTH// Retourne null si la valeur n'est pas valide
    }
}


