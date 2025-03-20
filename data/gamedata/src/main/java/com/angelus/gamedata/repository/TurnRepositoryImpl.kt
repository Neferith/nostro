package com.angelus.gamedata.repository

import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.entities.TurnList
import com.angelus.gamedomain.entities.TurnType
import com.angelus.gamedomain.repository.TurnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class TurnRepositoryImpl : TurnRepository {

    private val _turnList = MutableStateFlow(
        TurnList(
            listOf(
                Turn(TurnType.PLAYER("")),
                Turn(TurnType.NPC("GOBLIN"))
            ),
            0
        )
    )

    override fun observeTurn(): Flow<Turn> = _turnList.map { it.current }

    override fun nextTurn() {
        _turnList.value = _turnList.value.nextTurn()
    }
}