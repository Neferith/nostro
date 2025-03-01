package com.angelus.gamedomain.entities

sealed class TurnType {
    data class PLAYER(val id: String) : TurnType()
    data class NPC(val id: String) : TurnType()
}

data class Turn(
    val type: TurnType
)

data class TurnList(val turns: List<Turn>, val currentTurn: Int) {
    fun nextTurn(): TurnList {
        val next = (currentTurn + 1) % turns.size
        return copy(currentTurn = next)
    }

    val current: Turn
        get() = turns[currentTurn]
}