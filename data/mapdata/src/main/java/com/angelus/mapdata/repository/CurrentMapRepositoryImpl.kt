package com.angelus.mapdata.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.Size
import com.angelus.mapdomain.entities.GameMap
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.entities.TileType
import com.angelus.mapdomain.exception.MapNotFoundException
import com.angelus.mapdomain.repository.CurrentMapRepository
import com.angelus.mapdomain.repository.MoveType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

class CurrentMapRepositoryImpl(
    private val maps: Map<String, GameMap>
): CurrentMapRepository {

   private val dungeonGrid = arrayOf(
        intArrayOf(1, 1, 1, 1, 1),
        intArrayOf(1, 0, 0, 0, 1),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(1, 1, 1, 1, 1),
    )

    private val gameMap: GameMap by lazy {
        val gameMap = GameMap("","", Size(5, 6), TileType.STONE_WALL)
        for (y in dungeonGrid.indices) {
            for (x in 0..<dungeonGrid[y].size) {
                if(dungeonGrid[y][x] == 0) {
                    gameMap.setTileAt(Position(x,y),
                        Tile(TileType.STONE_FLOOR)
                    )
                }
            }
        }
        gameMap
    }

    // Initialisation du StateFlow avec un player par défaut
    private val _mapState = MutableStateFlow(
        gameMap
    )


    override suspend fun loadCurrentMap(id: String): GameMap {
        TODO("Not yet implemented")
    }

    override fun getPanorama(entityPosition: EntityPosition): com.angelus.mapdomain.entities.Panorama? {

        maps[entityPosition.mapId]?.let {
       return com.angelus.mapdomain.entities.Panorama(
           mapType = it.mapType,
           tiles = it.getPlayerGridVisibility(
               entityPosition,
               4
           )
       )
        }
        return null
    }

    override fun observeCurrentMap(): Flow<GameMap> =
        _mapState.mapNotNull { it }
            .distinctUntilChanged()

    override fun checkMoveInMap(
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

    override fun getTileAtPosition(entityPosition: EntityPosition): Result<Tile> {
        maps[entityPosition.mapId]?.let { map ->
            return Result.success(map.getTileAt(entityPosition.position))
        }
        return Result.failure(MapNotFoundException())
    }

}