package com.angelus.npc.domain.usecase

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.npc.domain.entities.NPC
import com.angelus.npc.domain.repository.NPCRepository


interface MoveNPCUseCase {
    data class Params(
        val characterId: String,
        val newPosition: EntityPosition
    )

    suspend operator fun invoke(params: Params): Result<NPC>
}

class MoveNPCUseCaseImpl(val repository: NPCRepository) : MoveNPCUseCase {
    override suspend fun invoke(params: MoveNPCUseCase.Params): Result<NPC> {
        return repository
            .fetchNPC(params.characterId)
            .mapCatching { npc ->
                val newNpc = npc.copy(entityPosition = params.newPosition)
                repository.updateNPC(newNpc)
                return Result.success(newNpc)
            }
    }

}