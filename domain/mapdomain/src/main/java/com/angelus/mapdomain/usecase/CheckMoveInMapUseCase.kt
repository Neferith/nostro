package com.angelus.mapdomain.usecase

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.mapdomain.repository.CurrentMapRepository
import com.angelus.mapdomain.repository.MoveType

data class CheckMoveParams(
    val position: EntityPosition,
    val direction: Direction
)

interface CheckMoveInMapUseCase {
    operator suspend fun invoke(params: CheckMoveParams): MoveType
}

class CheckMoveInMapUseCaseImpl(private val repository: CurrentMapRepository) :
    CheckMoveInMapUseCase {
    companion object {
        const val MOVE_DISTANCE = 1
    }

    override operator suspend fun invoke(params: CheckMoveParams): MoveType {
        return repository.checkMoveInMap(
            params.position,
            MOVE_DISTANCE,
            params.direction
        )
    }
}