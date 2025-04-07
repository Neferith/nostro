package com.angelus.gamedomain.factory

import com.angelus.gamedomain.repository.CurrentModuleRepository
import com.angelus.gamedomain.repository.GameSlotRepository
import com.angelus.gamedomain.usecase.FetchGameSlotUseCase
import com.angelus.gamedomain.usecase.FetchGameSlotUseCaseImpl
import com.angelus.gamedomain.usecase.NextTurnUseCase
import com.angelus.gamedomain.usecase.NextTurnUseCaseImpl

interface GameSlotRepositoryUseCaseFactory {

    val gameSlotRepository: GameSlotRepository

    fun makeFetchGameSlotUseCase(): FetchGameSlotUseCase {
        return FetchGameSlotUseCaseImpl(gameSlotRepository)
    }
}