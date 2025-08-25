package com.angelus.npc.domain.factory

import com.angelus.npc.domain.repository.TurnRepository
import com.angelus.npc.domain.usecase.FetchVisibleNCPUseCase
import com.angelus.npc.domain.usecase.FetchVisibleNCPUseCaseImpl
import com.angelus.npc.domain.usecase.MoveNPCUseCase
import com.angelus.npc.domain.usecase.MoveNPCUseCaseImpl
import com.angelus.npc.domain.usecase.NextTurnUseCase
import com.angelus.npc.domain.usecase.NextTurnUseCaseImpl
import com.angelus.npc.domain.usecase.ObserveTurnUseCase
import com.angelus.npc.domain.usecase.ObserveTurnUseCaseImpl


interface TurnUseCaseFactory {
    val turnRepository: TurnRepository

    fun makeObserveTurnUseCase(): ObserveTurnUseCase {
        return ObserveTurnUseCaseImpl(turnRepository)
    }

    fun makeNextTurnUseCase(): NextTurnUseCase {
        return NextTurnUseCaseImpl(turnRepository)
    }

    fun makeFetchVisibleNCPUseCase(): FetchVisibleNCPUseCase {
        return FetchVisibleNCPUseCaseImpl(turnRepository)
    }

    fun makeMoveNPCUseCase(): MoveNPCUseCase {
        return MoveNPCUseCaseImpl(turnRepository)
    }
}