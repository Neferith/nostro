package com.angelus.gamedata.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class TurnDTO(val type: TurnTypeDTO)

@kotlinx.serialization.Serializable
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