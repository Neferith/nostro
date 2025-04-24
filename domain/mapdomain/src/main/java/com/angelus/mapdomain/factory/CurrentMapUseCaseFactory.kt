package com.angelus.mapdomain.factory

import com.angelus.mapdomain.repository.CurrentMapRepository
import com.angelus.mapdomain.usecase.CheckMoveInMapUseCase
import com.angelus.mapdomain.usecase.CheckMoveInMapUseCaseImpl
import com.angelus.mapdomain.usecase.GetPanoramaUseCase
import com.angelus.mapdomain.usecase.GetPanoramaUseCaseImpl
import com.angelus.mapdomain.usecase.GetTileAtPosisitionUseCase
import com.angelus.mapdomain.usecase.GetTileAtPosisitionUseCaseImpl
import com.angelus.mapdomain.usecase.LoadCurrentMapUseCase
import com.angelus.mapdomain.usecase.LoadCurrentMapUseCaseImpl
import com.angelus.mapdomain.usecase.ObserveCurrentMapUseCase
import com.angelus.mapdomain.usecase.ObserveCurrentMapUseCaseImpl

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

    fun makeGetTileAtPosisitionUseCase(): GetTileAtPosisitionUseCase {
        return GetTileAtPosisitionUseCaseImpl(currentMapRepository)
    }
}