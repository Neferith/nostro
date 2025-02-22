package com.angelus.gamedomain.factory

import com.angelus.gamedomain.repository.CurrentMapRepository
import com.angelus.gamedomain.repository.PlayerRepository
import com.angelus.gamedomain.usecase.MovePlayerUseCase
import com.angelus.gamedomain.usecase.MovePlayerUseCaseImpl
import com.angelus.gamedomain.usecase.ObserveCurrentMapUseCase
import com.angelus.gamedomain.usecase.ObserveCurrentMapUseCaseImpl
import com.angelus.gamedomain.usecase.ObservePlayerUseCase
import com.angelus.gamedomain.usecase.ObservePlayerUseCaseImpl
import com.angelus.gamedomain.usecase.RotatePlayerUseCase
import com.angelus.gamedomain.usecase.RotatePlayerUseCaseImpl

interface PlayerUseCaseFactory {

    val playerRepository: PlayerRepository

    fun makeMovePlayerUseCase(): MovePlayerUseCase {
        return MovePlayerUseCaseImpl(playerRepository)
    }

    fun makeRotatePlayerUseCase(): RotatePlayerUseCase {
        return RotatePlayerUseCaseImpl(playerRepository)
    }

    fun makeObservePlayerUseCase(): ObservePlayerUseCase {
        return ObservePlayerUseCaseImpl(playerRepository)
    }
}

interface CurrentMapUseCaseFactory {

    val currentMapRepository: CurrentMapRepository

    fun makeObserveCurrentMapUseCase(): ObserveCurrentMapUseCase {
        return ObserveCurrentMapUseCaseImpl(currentMapRepository)
    }

   /* fun makeLoadCurrentMapUseCase(): LoadCurrentMapUseCase {
        return LoadCurrentMapUseCaseImpl(currentMapRepository)
    }*/

}