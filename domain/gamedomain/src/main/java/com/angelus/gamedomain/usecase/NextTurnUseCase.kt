package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.repository.TurnRepository
import kotlinx.coroutines.flow.Flow

interface NextTurnUseCase {
    suspend operator fun invoke()
}

class NextTurnUseCaseImpl(val turnRepository: TurnRepository): NextTurnUseCase {
    override suspend operator fun  invoke() {
        turnRepository.nextTurn()
    }
}