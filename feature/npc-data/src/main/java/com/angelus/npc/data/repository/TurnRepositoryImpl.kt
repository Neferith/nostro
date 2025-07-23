package com.angelus.npc.data.repository

import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.entities.TurnList
import com.angelus.gamedomain.entities.TurnType
import com.angelus.gamedomain.entities.npcTurnsAtPositions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

import com.angelus.npc.data.datasource.TurnDataSource
import com.angelus.npc.domain.repository.TurnRepository

class TurnRepositoryImpl(val dataSource: TurnDataSource) :
    TurnRepository {

    private val _turnList = MutableStateFlow<TurnList?>(null)

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        scope.launch {
            val turns = dataSource.fetchTurn()
            _turnList.value = turns
        }
    }

    override fun observeTurn(): Flow<Turn> = _turnList.mapNotNull { it?.current }

    override suspend fun nextTurn() {

        val currentTurn = _turnList.value?.nextTurn()
        currentTurn?.let {currentTurn ->
            dataSource.updateListTurn(currentTurn)
            _turnList.value = currentTurn
        }
    }

    override suspend fun fetchVisibleNCP(positions: List<Position>): List<TurnType.NPC> {
        return _turnList.value?.npcTurnsAtPositions(positions)?: emptyList()
    }
}