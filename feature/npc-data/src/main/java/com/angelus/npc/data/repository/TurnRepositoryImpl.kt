package com.angelus.npc.data.repository

import com.angelus.gamedomain.entities.Position
import com.angelus.npc.domain.entities.Turn
import com.angelus.npc.domain.entities.TurnList
import com.angelus.npc.domain.entities.TurnType
import com.angelus.npc.domain.entities.npcTurnsAtPositions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

import com.angelus.npc.data.datasource.TurnDataSource
import com.angelus.npc.domain.entities.npcTurnByCharacterId
import com.angelus.npc.domain.entities.updateTurn
import com.angelus.npc.domain.repository.TurnRepository

class NPCNotFounded(): Exception()

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

    override suspend fun fetchNPC(characterId: String): Result<TurnType.NPC> {
       val npc = _turnList.value?.npcTurnByCharacterId(characterId)
        if(npc != null) {
            return Result.success(npc)
        }
        return Result.failure(NPCNotFounded())
    }

    override suspend fun updateNPC(npc: TurnType.NPC): Result<TurnType.NPC> {
       val updatedTurn = _turnList.value?.updateTurn(npc.character.id, Turn(npc))
        updatedTurn?.let {currentTurn ->
            dataSource.updateListTurn(currentTurn)
            _turnList.value = currentTurn
        }
        return Result.success(npc)
    }
}