package com.angelus.playerdomain.factory


import com.angelus.playerdomain.usecase.MovePlayerUseCase
import com.angelus.playerdomain.usecase.MovePlayerUseCaseImpl
import com.angelus.playerdomain.usecase.ObservePlayerUseCase
import com.angelus.playerdomain.usecase.ObservePlayerUseCaseImpl
import com.angelus.playerdomain.usecase.RotatePlayerUseCase
import com.angelus.playerdomain.usecase.RotatePlayerUseCaseImpl
import com.angelus.playerdomain.repository.PlayerRepository

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

