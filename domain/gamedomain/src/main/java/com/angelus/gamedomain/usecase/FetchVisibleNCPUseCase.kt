package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.repository.TurnRepository

interface FetchVisibleNCPUseCase {
    suspend operator fun invoke(positions: List<Position>): List<Turn>
}

class FetchVisibleNCPUseCaseImpl(val repository: TurnRepository): FetchVisibleNCPUseCase {
    override suspend fun invoke(positions: List<Position>): List<Turn> {
        return repository.fetchVisibleNCP(positions)
    }

}