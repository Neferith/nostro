package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.Character

interface CreateNewGameUseCase {
    operator fun invoke(mainCharacter: Character)
}