package com.angelus.gamedomain.factory

import com.angelus.gamedomain.repository.GameSlotRepository
import com.angelus.gamedomain.usecase.FetchGameSlotUseCase
import com.angelus.gamedomain.usecase.FetchGameSlotUseCaseImpl

interface GameSlotRepositoryUseCaseFactory {

    val gameSlotRepository: GameSlotRepository

    fun makeFetchGameSlotUseCase(): FetchGameSlotUseCase {
        return FetchGameSlotUseCaseImpl(gameSlotRepository)
    }
}