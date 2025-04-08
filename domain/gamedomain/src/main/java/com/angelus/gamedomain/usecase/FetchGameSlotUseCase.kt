package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.GameSlot
import com.angelus.gamedomain.entities.character.Character
import com.angelus.gamedomain.repository.GameSlotRepository

interface FetchGameSlotUseCase {
    operator suspend fun invoke(): Result<List<GameSlot>>
}

class FetchGameSlotUseCaseImpl(val repository: GameSlotRepository): FetchGameSlotUseCase {
    override suspend fun invoke(): Result<List<GameSlot>> {
        return repository.fetchGameSlot()
    }
}