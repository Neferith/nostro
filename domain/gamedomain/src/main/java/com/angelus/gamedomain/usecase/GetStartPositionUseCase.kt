package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.repository.CurrentModuleRepository

interface GetStartPositionUseCase {
    operator fun invoke(): EntityPosition
}

class GetStartPositionUseCaseImpl(val repository: CurrentModuleRepository): GetStartPositionUseCase {
    override fun invoke(): EntityPosition {
        return repository.getStartPosition()
    }

}