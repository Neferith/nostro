package com.angelus.playerdata.data.mapper

import PlayerBandDTO
import PlayerDTO
import com.angelus.gamedata.data.dto.EntityPositionDTO
import com.angelus.gamedata.data.dto.PositionDTO
import com.angelus.gamedata.data.mapper.convertCharacterFromDTO
import com.angelus.gamedata.data.mapper.convertCharacterToDTO
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Orientation
import com.angelus.gamedomain.entities.Position
import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.entities.PlayerBand

fun PlayerBand.convertToDTO(): PlayerBandDTO {

    return PlayerBandDTO(
        characters = this.characters.map { it.convertCharacterToDTO() }
    )
}

fun PlayerBandDTO.convertFromDTO(): PlayerBand {
    return PlayerBand(
        characters = this.characters.map { it.convertCharacterFromDTO() }
    )
}

fun Player.convertToDTO(): PlayerDTO {
    val positionDTO = PositionDTO(this.entityPosition.x, this.entityPosition.y)

    val entityPositionDTO = EntityPositionDTO(
        this.entityPosition.mapId,
        positionDTO,
        this.entityPosition.orientation.toString()
    )
    return PlayerDTO(this.id, entityPositionDTO, this.band.convertToDTO())
}

fun PlayerDTO.convertFromDTO(): Player {
    val position = Position(this.entityPosition.position.x, this.entityPosition.position.y)

    val entityPosition = EntityPosition(
        this.entityPosition.mapId,
        position,
        this.entityPosition.orientation.toOrientation()
    )
    return Player(this.id,
        entityPosition,
        this.playerBand.convertFromDTO()
    )
}

fun String.toOrientation(): Orientation {
    return try {
        Orientation.valueOf(this.uppercase())
    } catch (e: IllegalArgumentException) {
        return Orientation.NORTH// Retourne null si la valeur n'est pas valide
    }
}