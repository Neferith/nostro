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
}