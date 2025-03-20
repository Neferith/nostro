package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.repository.TurnRepository
import kotlinx.coroutines.flow.Flow

interface NextTurnUseCase {
    operator fun invoke()
}

class NextTurnUseCaseImpl(val turnRepository: TurnRepository): NextTurnUseCase {
    override fun invoke() {
        turnRepository.nextTurn()
    }
}