package com.angelus.gamedomain.entities

data class GameMap(val id: String,
                   val size: Size,
                   val defaultTileType: TileType) {
    private val tiles: MutableMap<Position, Tile> = mutableMapOf()

    fun getTileAt(position: Position): Tile {
        return if (isWithinBounds(position)) {
            tiles[position] ?: Tile(defaultTileType)
        } else {
            Tile(defaultTileType, walkable = false) // Tile hors-limites
        }
    }

    // Définit une tile à une position donnée
    fun setTileAt(position: Position, tile: Tile) {
        if (isWithinBounds(position)) {
            tiles[position] = tile
        } else {
            println("Warning: Attempted to place a tile outside the map at $position")
        }
    }

    private fun isWithinBounds(position: Position): Boolean {
        return position.x in 0 until size.width && position.y in 0 until size.height
    }

    // Récupère une portion (sous-grille) de la carte
    fun getGrid(start: Position, width: Int, height: Int): List<List<Tile>> {
        val grid = mutableListOf<List<Tile>>()
        for (y in start.y until start.y + height) {
            val row = mutableListOf<Tile>()
            for (x in start.x until start.x + width) {
                // Ajouter la tile à la ligne, en s'assurant de ne pas sortir des limites
                row.add(getTileAt(Position(x, y)))
            }
            grid.add(row)
        }
        return grid
    }

    fun getPlayerGridVisibility(
        playerPosition: EntityPosition,
        maxDepth: Int
    ):List<List<Tile>>{

        val width = 3 + 2 * (maxDepth - 1)
        val visibleTiles = mutableListOf<List<Tile>>()
        for (depth in 1..maxDepth) {
            val row = mutableListOf<Tile>()
            for (i in -width / 2..width / 2) {
                val (x, y) = when (playerPosition.orientation) {
                    Orientation.NORTH -> Pair(
                        playerPosition.x + i,
                        playerPosition.y - depth
                    )
                    Orientation.SOUTH -> Pair(
                        playerPosition.x + i,
                        playerPosition.y + depth
                    )
                    Orientation.EAST -> Pair(
                        playerPosition.x + depth,
                        playerPosition.y + i
                    )
                    Orientation.WEST -> Pair(
                        playerPosition.x - depth,
                        playerPosition.y + i
                    )
                }
                row.add(getTileAt(Position(x,y)))
            }
            visibleTiles.add(row)
        }
        return visibleTiles.asReversed()
    }



    fun getVisibleAndHiddenTiles(
        map: Array<Array<Tile>>,
        playerX: Int,
        playerY: Int,
        direction: Orientation,
        maxDepth: Int,
        baseWidth: Int
    ): Pair<List<List<Tile>>, List<Tile>> {
        val visibleTiles = mutableListOf<List<Tile>>()
        val allVisiblePositions = mutableSetOf<Pair<Int, Int>>()

        for (depth in 1..maxDepth) {
            val width = baseWidth + 2 * (depth - 1)
            val row = mutableListOf<Tile>()

            for (i in -width / 2..width / 2) {
                val (x, y) = when (direction) {
                    Orientation.NORTH -> Pair(playerX + i, playerY - depth)
                    Orientation.SOUTH -> Pair(playerX + i, playerY + depth)
                    Orientation.EAST  -> Pair(playerX + depth, playerY + i)
                    Orientation.WEST  -> Pair(playerX - depth, playerY + i)
                }

                if (x in map.indices && y in map[0].indices) {
                    val tile = map[x][y]
                    row.add(tile)
                    allVisiblePositions.add(Pair(x, y))

                    if (tile.type.isWall == true) {
                        // Bloque la vision au-delà de ce mur
                        break
                    }
                }
            }
            visibleTiles.add(row)
        }

        // Récupérer les cases invisibles
        val hiddenTiles = mutableListOf<Tile>()
        for (y in map[0].indices) {
            for (x in map.indices) {
                if (!allVisiblePositions.contains(Pair(x, y))) {
                    hiddenTiles.add(map[x][y])
                }
            }
        }

        return Pair(visibleTiles, hiddenTiles)
    }
}