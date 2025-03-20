package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.BackgroundType
import com.angelus.gamedomain.repository.CurrentModuleRepository

interface GetAllBackgroundStoriesUseCase {
    operator fun invoke(): List<BackgroundType>
}

class GetAllBackgroundStoriesUseCaseImpl(
    val currentModuleRepository: CurrentModuleRepository
): GetAllBackgroundStoriesUseCase {
    override fun invoke(): List<BackgroundType> {
       return currentModuleRepository.getAllBackgroundStories()
    }
}