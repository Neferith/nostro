package com.angelus.data.gamedata.repository

import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.GameMap
import com.angelus.gamedomain.entities.Panorama
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.Size
import com.angelus.gamedomain.entities.Tile
import com.angelus.gamedomain.entities.TileType
import com.angelus.gamedomain.repository.CurrentMapRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

class CurrentMapRepositoryImpl: CurrentMapRepository {

    private val dungeonGrid = arrayOf(
        intArrayOf(1, 1, 1, 1, 1),
        intArrayOf(1, 0, 0, 0, 1),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(1, 1, 1, 1, 1),
    )

    private val gameMap: GameMap by lazy {
        val gameMap = GameMap("", Size(5, 6), TileType.STONE_WALL)
        for (y in 0 .. dungeonGrid.size -1) {
            for (x in 0 .. dungeonGrid[y].size -1) {
                if(dungeonGrid[y][x] == 0) {
                    gameMap.setTileAt(Position(x,y), Tile(TileType.STONE_FLOOR))
                }
            }
        }
        gameMap
    }

    // Initialisation du StateFlow avec un player par défaut
    private val _mapState = MutableStateFlow<GameMap>(
        gameMap
    )


    override suspend fun loadCurrentMap(id: String): GameMap {
        TODO("Not yet implemented")
    }

    override fun getPanorama(entityPosition: EntityPosition): Panorama {
       return Panorama(gameMap.getPlayerGridVisibility(entityPosition, 4))
    }

    override fun observeCurrentMap(): Flow<GameMap> =
        _mapState.mapNotNull { it }
            .distinctUntilChanged()

    override fun checkMoveInMap(
        entityPosition: EntityPosition,
        moveDistance: Int,
        direction: Direction
    ): Boolean {
        _mapState.value.let { map ->
            // On s'assure de ne pas modifier un objet existant.
            val checkPosition =
                EntityPosition(entityPosition.position.copy(), entityPosition.orientation)
            for (i in 1..moveDistance) {
                val nextPosition = checkPosition.changePosition(1, direction)
                if (!map.getTileAt(nextPosition.position).walkable) {
                    return false
                }
            }
            return true
        }
        return false
    }

}