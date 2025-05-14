package com.angelus.mapdata.base.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.mapdomain.entities.GameMap
import com.angelus.mapdomain.entities.Panorama
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.exception.MapNotFoundException
import com.angelus.mapdomain.repository.CurrentMapRepository
import com.angelus.mapdomain.repository.MoveType

class BaseMapRepository(
    private val maps: Map<String, GameMap>
): CurrentMapRepository {

    override suspend fun getPanorama(entityPosition: EntityPosition): Panorama? {

        maps[entityPosition.mapId]?.let {
       return Panorama(
           mapType = it.mapType,
           tiles = it.getPlayerGridVisibility(
               entityPosition,
               4
           )
       )
        }
        return null
    }

    override suspend fun checkMoveInMap(
        entityPosition: EntityPosition,
        moveDistance: Int,
        direction: Direction
    ): MoveType {
        maps[entityPosition.mapId]?.let { map ->
            // On s'assure de ne pas modifier un objet existant.
            val checkPosition =
                EntityPosition(
                    entityPosition.mapId,
                    entityPosition.position.copy(),
                    entityPosition.orientation
                )
            for (i in 1..moveDistance) {
                val nextPosition = checkPosition.changePosition(1, direction)
                if (!map.getTileAt(nextPosition.position).walkable) {
                    return MoveType.blocked
                }
                val transition = map.getTileAt(nextPosition.position).transition
                if (transition != null) {
                    return MoveType.transition(transition)
                }
            }
            return MoveType.walkable
        }
        return MoveType.blocked
    }

    override suspend fun getTileAtPosition(entityPosition: EntityPosition): Result<Tile> {
        maps[entityPosition.mapId]?.let { map ->
            return Result.success(map.getTileAt(entityPosition.position))
        }
        return Result.failure(MapNotFoundException())
    }

    override suspend fun addObjectToTileUseCase(
        entityPosition: EntityPosition,
        objectId: String,
        quantity: Int
    ): Result<Tile> {
        TODO("Not yet implemented")
    }

    override suspend fun removeObjectToTileUseCase(
        entityPosition: EntityPosition,
        objectId: String,
        quantity: Int
    ): Result<Tile> {
        TODO("Not yet implemented")
    }

}