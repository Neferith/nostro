package com.angelus.gamedomain.factory

import com.angelus.gamedomain.repository.CurrentMapRepository
import com.angelus.gamedomain.usecase.CheckMoveInMapUseCase
import com.angelus.gamedomain.usecase.CheckMoveInMapUseCaseImpl
import com.angelus.gamedomain.usecase.GetPanoramaUseCase
import com.angelus.gamedomain.usecase.GetPanoramaUseCaseImpl
import com.angelus.gamedomain.usecase.LoadCurrentMapUseCase
import com.angelus.gamedomain.usecase.LoadCurrentMapUseCaseImpl
import com.angelus.gamedomain.usecase.ObserveCurrentMapUseCase
import com.angelus.gamedomain.usecase.ObserveCurrentMapUseCaseImpl

interface CurrentMapUseCaseFactory {
    val currentMapRepository: CurrentMapRepository

    fun makeObserveCurrentMapUseCase(): ObserveCurrentMapUseCase {
        return ObserveCurrentMapUseCaseImpl(currentMapRepository)
    }

    fun makeLoadCurrentMapUseCase(): LoadCurrentMapUseCase {
        return LoadCurrentMapUseCaseImpl(currentMapRepository)
    }

    fun makeFetchPanorameUseCase(): GetPanoramaUseCase {
        return GetPanoramaUseCaseImpl(currentMapRepository)
    }

    fun makeCheckMoveInMapUseCase(): CheckMoveInMapUseCase {
        return CheckMoveInMapUseCaseImpl(currentMapRepository)
    }
}