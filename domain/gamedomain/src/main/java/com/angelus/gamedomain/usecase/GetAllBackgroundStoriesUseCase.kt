package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.BackgroundType
import com.angelus.gamedomain.repository.CurrentGameRepository

interface GetAllBackgroundStoriesUseCase {
    operator fun invoke(): List<BackgroundType>
}

class GetAllBackgroundStoriesUseCaseImpl(
    val currentGameRepository: CurrentGameRepository
): GetAllBackgroundStoriesUseCase {
    override fun invoke(): List<BackgroundType> {
       return currentGameRepository.getAllBackgroundStories()
    }
}