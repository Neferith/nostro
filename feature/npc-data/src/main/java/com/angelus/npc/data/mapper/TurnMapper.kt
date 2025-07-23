package com.angelus.npc.data.mapper

import com.angelus.gamedata.data.mapper.convertFromDTO
import com.angelus.gamedata.data.mapper.convertToDTO
import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.entities.TurnList
import com.angelus.gamedomain.entities.TurnType

fun TurnList.convertToDTO(): com.angelus.npc.data.dto.TurnListDTO {
    return com.angelus.npc.data.dto.TurnListDTO(
        turns = this.turns.map { it.convertToDTO() },
        current = this.currentTurn
    )
}

fun com.angelus.npc.data.dto.TurnListDTO.convertFromDTO(): TurnList {
    return TurnList(
        turns = this.turns.map { it.convertFromDTO() },
        currentTurn = this.current
    )
}


fun Turn.convertToDTO(): com.angelus.npc.data.dto.TurnDTO {
    return com.angelus.npc.data.dto.TurnDTO(
        type = type.toDTO()
    )
}

fun com.angelus.npc.data.dto.TurnDTO.convertFromDTO(): Turn {
    return Turn(
        type = type.toDomain()
    )
}

fun TurnType.toDTO(): com.angelus.npc.data.dto.TurnTypeDTO = when (this) {
    is TurnType.PLAYER -> com.angelus.npc.data.dto.TurnTypeDTO.PLAYER(this.id)
    is TurnType.NPC -> com.angelus.npc.data.dto.TurnTypeDTO.NPC(character.convertToDTO(), entityPosition.convertToDTO())
}

fun com.angelus.npc.data.dto.TurnTypeDTO.toDomain(): TurnType = when (this) {
    is com.angelus.npc.data.dto.TurnTypeDTO.PLAYER -> TurnType.PLAYER(this.id)
    is com.angelus.npc.data.dto.TurnTypeDTO.NPC -> TurnType.NPC(character.convertFromDTO(), entityPosition.convertFromDTO())
}
