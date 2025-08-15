package com.angelus.npc.domain.entities

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.character.Character

sealed class TurnType {
    data class PLAYER(val id: String) : TurnType()
    data class NPC(
        val character: Character,
        val entityPosition: EntityPosition
    ) : TurnType()
}

data class Turn(
    val type: TurnType
)

data class TurnList(
    val turns: List<Turn>,
    val currentTurn: Int
) {
    fun nextTurn(): TurnList {
        val next = (currentTurn + 1) % turns.size
        return copy(currentTurn = next)
    }

    val current: Turn
        get() = turns[currentTurn]
}

fun TurnList.updateTurn(characterId: String, newTurn: Turn): TurnList {
    val index = turns.indexOfFirst {
        when (val type = it.type) {
            is TurnType.NPC -> type.character.id == characterId
            is TurnType.PLAYER -> false
        }
    }

    require(index != -1) { "Turn introuvable dans la liste" }

    val updatedTurns = turns.toMutableList().also { it[index] = newTurn }
    return copy(turns = updatedTurns)
}


fun TurnList.npcTurnsAtPositions(positions: List<Position>): List<TurnType.NPC> {
    return turns
        .map { it.type }
        .filterIsInstance<TurnType.NPC>()
        .filter { it.entityPosition.position in positions }
}

fun TurnList.npcTurnByCharacterId(characterId: String): TurnType.NPC? {
    return turns
        .map { it.type }
        .filterIsInstance<TurnType.NPC>()
        .firstOrNull { it.character.id == characterId }
}