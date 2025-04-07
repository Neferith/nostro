package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.character.Character

interface CreateNewGameUseCase {
    operator fun invoke(mainCharacter: Character)
}