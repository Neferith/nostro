package com.angelus.playerdomain.factory


import com.angelus.playerdomain.usecase.MovePlayerUseCase
import com.angelus.playerdomain.usecase.MovePlayerUseCaseImpl
import com.angelus.playerdomain.usecase.ObservePlayerUseCase
import com.angelus.playerdomain.usecase.ObservePlayerUseCaseImpl
import com.angelus.playerdomain.usecase.RotatePlayerUseCase
import com.angelus.playerdomain.usecase.RotatePlayerUseCaseImpl
import com.angelus.playerdomain.repository.PlayerRepository
import com.angelus.playerdomain.usecase.AddObjectToPlayerUseCase
import com.angelus.playerdomain.usecase.AddObjectToPlayerUseCaseImpl
import com.angelus.playerdomain.usecase.ChangePlayerZoneUseCase
import com.angelus.playerdomain.usecase.ChangePlayerZoneUseCaseImpl
import com.angelus.playerdomain.usecase.InitializePlayerUseCase
import com.angelus.playerdomain.usecase.InitializePlayerUseCaseImpl
import com.angelus.playerdomain.usecase.RemoveObjectToPlayerUseCase
import com.angelus.playerdomain.usecase.RemoveObjectToPlayerUseCaseImpl

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

    fun makeInitializePlayerUseCase(): InitializePlayerUseCase {
        return InitializePlayerUseCaseImpl(playerRepository)
    }

    fun makeChangePlayerZoneUseCase(): ChangePlayerZoneUseCase {
        return ChangePlayerZoneUseCaseImpl(playerRepository)
    }

    fun makeAddObjectToPlayerUseCase(): AddObjectToPlayerUseCase {
        return AddObjectToPlayerUseCaseImpl(playerRepository)
    }

    fun makeRemoveObjectToPlayerUseCase(): RemoveObjectToPlayerUseCase {
        return RemoveObjectToPlayerUseCaseImpl(playerRepository)
    }
}

