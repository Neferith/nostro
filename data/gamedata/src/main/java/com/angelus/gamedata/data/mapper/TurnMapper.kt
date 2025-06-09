package com.angelus.gamedata.data.mapper

import com.angelus.gamedata.data.dto.TurnDTO
import com.angelus.gamedata.data.dto.TurnListDTO
import com.angelus.gamedata.data.dto.TurnTypeDTO
import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.entities.TurnList
import com.angelus.gamedomain.entities.TurnType

fun TurnList.convertToDTO(): TurnListDTO {
    return TurnListDTO(
        turns = this.turns.map { it.convertToDTO() },
        current = this.currentTurn
    )
}

fun TurnListDTO.convertFromDTO(): TurnList {
    return TurnList(
        turns = this.turns.map { it.convertFromDTO() },
        currentTurn = this.current
    )
}


fun Turn.convertToDTO(): TurnDTO {
    return TurnDTO(
        type = type.toDTO()
    )
}

fun TurnDTO.convertFromDTO(): Turn {
    return Turn(
        type = type.toDomain()
    )
}

fun TurnType.toDTO(): TurnTypeDTO = when (this) {
    is TurnType.PLAYER -> TurnTypeDTO.PLAYER(this.id)
    is TurnType.NPC -> TurnTypeDTO.NPC(character.convertToDTO(), entityPosition.convertToDTO())
}

fun TurnTypeDTO.toDomain(): TurnType = when (this) {
    is TurnTypeDTO.PLAYER -> TurnType.PLAYER(this.id)
    is TurnTypeDTO.NPC -> TurnType.NPC(character.convertFromDTO(), entityPosition.convertFromDTO())
}
