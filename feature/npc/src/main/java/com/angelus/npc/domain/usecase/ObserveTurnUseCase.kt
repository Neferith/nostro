package com.angelus.npc.domain.usecase

import com.angelus.npc.domain.entities.Turn
import com.angelus.npc.domain.entities.TurnList
import com.angelus.npc.domain.repository.TurnRepository
import kotlinx.coroutines.flow.Flow

interface ObserveTurnUseCase {
    operator fun invoke(): Flow<TurnList>
}

class ObserveTurnUseCaseImpl(val turnRepository: TurnRepository): ObserveTurnUseCase {
    override fun invoke(): Flow<TurnList> {
        return turnRepository.observeTurn()
    }
}