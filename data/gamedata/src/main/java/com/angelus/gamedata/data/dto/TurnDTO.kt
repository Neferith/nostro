package com.angelus.gamedata.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TurnListDTO(val turns: List<TurnDTO>, val current : Int)

@Serializable
data class TurnDTO(val type: TurnTypeDTO)

@Serializable
sealed class TurnTypeDTO {
    @Serializable
    @SerialName("PLAYER")
    data class PLAYER(val id: String) : TurnTypeDTO()

    @Serializable
    @SerialName("NPC")
    data class NPC(
        val character: CharacterDTO,
        val entityPosition: EntityPositionDTO
    ) : TurnTypeDTO()
}