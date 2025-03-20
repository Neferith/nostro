package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.repository.TurnRepository
import kotlinx.coroutines.flow.Flow

interface ObserveTurnUseCase {
    operator fun invoke(): Flow<Turn>
}

class ObserveTurnUseCaseImpl(val turnRepository: TurnRepository): ObserveTurnUseCase {
    override fun invoke(): Flow<Turn> {
        return turnRepository.observeTurn()
    }
}