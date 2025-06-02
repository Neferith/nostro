package com.angelus.gamedata.repository

import com.angelus.gamedata.data.TurnDataSource
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.entities.TurnList
import com.angelus.gamedomain.entities.TurnType
import com.angelus.gamedomain.entities.npcTurnsAtPositions
import com.angelus.gamedomain.repository.TurnRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

class TurnRepositoryImpl(val dataSource: TurnDataSource) : TurnRepository {

    private val _turnList = MutableStateFlow<TurnList?>(null)
//    val turnList: StateFlow<List<Turn>> = _turnList

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        scope.launch {
            val turns = dataSource.fetchTurn()
            _turnList.value = turns
        }
    }

  /*  suspend fun loadTurns() {
        val turns = dataSource.fetchTurn()
        _turnList.value = turns
    }

    suspend fun updateTurns(turns: List<Turn>) {
        dataSource.updateListTurn(turns)
        _turnList.value = turns
    }*/

    override fun observeTurn(): Flow<Turn> = _turnList.mapNotNull { it?.current }

    override fun nextTurn() {
        _turnList.value = _turnList.value?.nextTurn()
    }

    override suspend fun fetchVisibleNCP(positions: List<Position>): List<TurnType.NPC> {
        return _turnList.value?.npcTurnsAtPositions(positions)?: emptyList()
    }
}