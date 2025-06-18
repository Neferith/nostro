package com.angelus.faction.domain.usecase

import com.angelus.faction.domain.entities.Relation
import com.angelus.faction.domain.repository.FactionRepository

interface CheckFactionUseCase {
    suspend operator fun invoke(faction1Id: String, faction2Id: String): Relation

}

class CheckFactionUseCaseImpl(val repository: FactionRepository): CheckFactionUseCase {
    override suspend operator fun invoke(faction1Id: String, faction2Id: String): Relation {
        val faction1 = repository.fetchFaction(faction1Id)
       // val faction2 = repository.fetchFaction(faction2Id)
        return faction1?.checkHostility(faction2Id)?: Relation.NEUTRAL
    }

}