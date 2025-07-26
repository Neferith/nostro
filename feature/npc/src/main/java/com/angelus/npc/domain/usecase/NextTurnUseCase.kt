package com.angelus.npc.domain.usecase

import com.angelus.npc.domain.repository.TurnRepository

interface NextTurnUseCase {
    suspend operator fun invoke()
}

class NextTurnUseCaseImpl(val turnRepository: TurnRepository): NextTurnUseCase {
    override suspend operator fun  invoke() {
        turnRepository.nextTurn()
    }
}