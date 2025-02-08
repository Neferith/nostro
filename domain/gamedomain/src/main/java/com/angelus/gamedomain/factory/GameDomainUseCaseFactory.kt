package com.angelus.gamedomain.factory

import com.angelus.gamedomain.repository.PlayerRepository
import com.angelus.gamedomain.usecase.MovePlayerUseCase
import com.angelus.gamedomain.usecase.MovePlayerUseCaseImpl
import com.angelus.gamedomain.usecase.RotatePlayerUseCase
import com.angelus.gamedomain.usecase.RotatePlayerUseCaseImpl

interface GameDomainUseCaseFactory {
    fun makeMovePlayerUseCase(repository: PlayerRepository): MovePlayerUseCase {
        return MovePlayerUseCaseImpl(repository)
    }

    fun makeRotatePlayerUseCase(repository: PlayerRepository): RotatePlayerUseCase {
        return RotatePlayerUseCaseImpl(repository)
    }
}