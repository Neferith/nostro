package com.angelus.mapdomain.factory

import com.angelus.mapdomain.repository.CurrentMapRepository
import com.angelus.mapdomain.usecase.CheckMoveInMapUseCase
import com.angelus.mapdomain.usecase.CheckMoveInMapUseCaseImpl
import com.angelus.mapdomain.usecase.GetPanoramaUseCase
import com.angelus.mapdomain.usecase.GetPanoramaUseCaseImpl
import com.angelus.mapdomain.usecase.GetTileAtPosisitionUseCase
import com.angelus.mapdomain.usecase.GetTileAtPosisitionUseCaseImpl

interface CurrentMapUseCaseFactory {
    val currentMapRepository: CurrentMapRepository

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