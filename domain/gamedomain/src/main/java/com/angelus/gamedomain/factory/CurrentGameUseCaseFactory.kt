package com.angelus.gamedomain.factory

import com.angelus.gamedomain.repository.CurrentGameRepository
import com.angelus.gamedomain.repository.TurnRepository
import com.angelus.gamedomain.usecase.GetAllBackgroundStoriesUseCase
import com.angelus.gamedomain.usecase.GetAllBackgroundStoriesUseCaseImpl
import com.angelus.gamedomain.usecase.NextTurnUseCase
import com.angelus.gamedomain.usecase.NextTurnUseCaseImpl
import com.angelus.gamedomain.usecase.ObserveTurnUseCase
import com.angelus.gamedomain.usecase.ObserveTurnUseCaseImpl

interface CurrentGameUseCaseFactory {

        val currentGameRepository: CurrentGameRepository

        fun makeGetAGetAllBackgroundStoriesUseCase(): GetAllBackgroundStoriesUseCase {
            return GetAllBackgroundStoriesUseCaseImpl(currentGameRepository)
        }

}