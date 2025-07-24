package com.angelus.npc.domain.usecase

import com.angelus.npc.domain.entities.Turn
import com.angelus.npc.domain.repository.TurnRepository
import kotlinx.coroutines.flow.Flow

interface ObserveTurnUseCase {
    operator fun invoke(): Flow<Turn>
}

class ObserveTurnUseCaseImpl(val turnRepository: TurnRepository): ObserveTurnUseCase {
    override fun invoke(): Flow<Turn> {
        return turnRepository.observeTurn()
    }
}