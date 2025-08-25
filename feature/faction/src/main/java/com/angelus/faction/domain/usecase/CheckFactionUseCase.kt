package com.angelus.faction.domain.usecase

import com.angelus.faction.domain.entities.Relation
import com.angelus.faction.domain.repository.FactionRepository

interface CheckFactionUseCase {
    suspend operator fun invoke(faction1Id: String, faction2Id: String): Relation

}

class CheckFactionUseCaseImpl(val repository: FactionRepository): CheckFactionUseCase {
    override suspend operator fun invoke(faction1Id: String, faction2Id: String): Relation {
        val faction1 = repository.fetchFaction(faction1Id)
        return faction1.getOrNull()?.checkHostility(faction2Id)?: Relation.NEUTRAL
    }

}