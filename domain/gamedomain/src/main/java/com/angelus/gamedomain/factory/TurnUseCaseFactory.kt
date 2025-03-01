package com.angelus.gamedomain.factory

import com.angelus.gamedomain.repository.TurnRepository
import com.angelus.gamedomain.usecase.NextTurnUseCase
import com.angelus.gamedomain.usecase.NextTurnUseCaseImpl
import com.angelus.gamedomain.usecase.ObserveTurnUseCase
import com.angelus.gamedomain.usecase.ObserveTurnUseCaseImpl


interface TurnUseCaseFactory {
    val turnRepository: TurnRepository

    fun makeObserveTurnUseCase(): ObserveTurnUseCase {
        return ObserveTurnUseCaseImpl(turnRepository)
    }

    fun makeNextTurnUseCase(): NextTurnUseCase {
        return NextTurnUseCaseImpl(turnRepository)
    }
}