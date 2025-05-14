package com.angelus.gamedomain.factory

import com.angelus.gamedomain.repository.CurrentModuleRepository
import com.angelus.gamedomain.usecase.FetchItemsByIdUseCase
import com.angelus.gamedomain.usecase.FetchItemsByIdUseCaseImpl
import com.angelus.gamedomain.usecase.GetAllBackgroundStoriesUseCase
import com.angelus.gamedomain.usecase.GetAllBackgroundStoriesUseCaseImpl
import com.angelus.gamedomain.usecase.GetStartPositionUseCase
import com.angelus.gamedomain.usecase.GetStartPositionUseCaseImpl

interface CurrentGameUseCaseFactory {

    val currentModuleRepository: CurrentModuleRepository

    fun makeGetAGetAllBackgroundStoriesUseCase(): GetAllBackgroundStoriesUseCase {
        return GetAllBackgroundStoriesUseCaseImpl(currentModuleRepository)
    }

    fun makeGetStartPositionUseCase(): GetStartPositionUseCase {
        return GetStartPositionUseCaseImpl(currentModuleRepository)
    }

    fun makeFetchItemsByIdUseCase(): FetchItemsByIdUseCase {
        return FetchItemsByIdUseCaseImpl(currentModuleRepository)
    }


}