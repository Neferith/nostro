package com.angelus.gamedomain.factory

import com.angelus.gamedomain.repository.CurrentModuleRepository
import com.angelus.gamedomain.usecase.CreateNewGameUseCase
import com.angelus.gamedomain.usecase.GetAllBackgroundStoriesUseCase
import com.angelus.gamedomain.usecase.GetAllBackgroundStoriesUseCaseImpl

interface CurrentGameUseCaseFactory {

        val currentModuleRepository: CurrentModuleRepository

        fun makeGetAGetAllBackgroundStoriesUseCase(): GetAllBackgroundStoriesUseCase {
            return GetAllBackgroundStoriesUseCaseImpl(currentModuleRepository)
        }

   // fun makeCreateNewGameUseCase(): CreateNewGameUseCase

}