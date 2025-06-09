package com.angelus.mapdomain.factory

import com.angelus.mapdomain.repository.CurrentMapRepository
import com.angelus.mapdomain.usecase.AddObjectToTileUseCase
import com.angelus.mapdomain.usecase.AddObjectToTileUseCaseImpl
import com.angelus.mapdomain.usecase.CheckMoveInMapUseCase
import com.angelus.mapdomain.usecase.CheckMoveInMapUseCaseImpl
import com.angelus.mapdomain.usecase.CheckVisibilityUseCase
import com.angelus.mapdomain.usecase.CheckVisibilityUseCaseImpl
import com.angelus.mapdomain.usecase.GetPanoramaUseCase
import com.angelus.mapdomain.usecase.GetPanoramaUseCaseImpl
import com.angelus.mapdomain.usecase.GetTileAtPosisitionUseCase
import com.angelus.mapdomain.usecase.GetTileAtPosisitionUseCaseImpl
import com.angelus.mapdomain.usecase.RemoveObjectToTileUseCase
import com.angelus.mapdomain.usecase.RemoveObjectToTileUseCaseImpl

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

    fun makeAddObjectToTileUseCase(): AddObjectToTileUseCase {
        return AddObjectToTileUseCaseImpl(currentMapRepository)
    }

    fun makeRemoveObjectToTileUseCase(): RemoveObjectToTileUseCase {
        return RemoveObjectToTileUseCaseImpl(currentMapRepository)
    }

    fun makeCheckVisibilityUseCase(): CheckVisibilityUseCase {
        return CheckVisibilityUseCaseImpl(currentMapRepository)
    }
}