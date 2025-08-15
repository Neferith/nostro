package com.angelus.mapdomain.usecase

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Orientation
import com.angelus.gamedomain.entities.Position
import com.angelus.mapdomain.entities.GameMap
import com.angelus.mapdomain.entities.Tile
import com.angelus.mapdomain.entities.TileType
import com.angelus.mapdomain.repository.CurrentMapRepository

interface CheckVisibilityUseCase {
    suspend operator fun invoke(source: EntityPosition, target: EntityPosition, distance: Int): EntityPosition?
}


class CheckVisibilityUseCaseImpl(
    private val repository: CurrentMapRepository,
    private val blocksVision: (Tile) -> Boolean = { tile -> tile.type == TileType.STONE_WALL }
): CheckVisibilityUseCase{
    override suspend fun invoke(
        source: EntityPosition,
        target: EntityPosition,
        distance: Int
    ): EntityPosition? {
        val map: GameMap? = repository.fetchMapById(source.mapId).getOrNull()
        if(map == null) {
            return null
        }
        if (source.mapId != target.mapId) return null


        val dx = target.position.x - source.position.x
        val dy = target.position.y - source.position.y
        val dist = manhattan(source.position, target.position)
        if (dist == 0 || dist > distance) return null

        if (!isInVisionCone(source.orientation, dx, dy)) return null

        val line = bresenhamLine(source.position, target.position).drop(1)

        for ((i, pos) in line.withIndex()) {
            if (i >= distance) break

            val tile = map.tiles[pos] ?: Tile(map.defaultTileType)

            if (blocksVision(tile)) break // Vision bloquée ici

            if (tile.walkable) {
                // @TODO : Ici on devrait gérer l'orientation et la map ID si cela à changé
                return EntityPosition(source.mapId, pos, source.orientation) // ✅ Première case visible & walkable
            }
        }

        return null
    }

    private fun manhattan(a: Position, b: Position): Int =
        kotlin.math.abs(a.x - b.x) + kotlin.math.abs(a.y - b.y)

    private fun isInVisionCone(orientation: Orientation, dx: Int, dy: Int): Boolean {
        return when (orientation) {
            Orientation.NORTH -> dy < 0
            Orientation.SOUTH -> dy > 0
            Orientation.WEST  -> dx < 0
            Orientation.EAST  -> dx > 0
        }
    }

}


fun bresenhamLine(start: Position, end: Position): List<Position> {
    val points = mutableListOf<Position>()
    var x0 = start.x
    var y0 = start.y
    val x1 = end.x
    val y1 = end.y

    val dx = kotlin.math.abs(x1 - x0)
    val dy = kotlin.math.abs(y1 - y0)
    val sx = if (x0 < x1) 1 else -1
    val sy = if (y0 < y1) 1 else -1
    var err = dx - dy

    while (true) {
        points.add(Position(x0, y0))
        if (x0 == x1 && y0 == y1) break
        val e2 = 2 * err
        if (e2 > -dy) {
            err -= dy
            x0 += sx
        }
        if (e2 < dx) {
            err += dx
            y0 += sy
        }
    }
    return points
}