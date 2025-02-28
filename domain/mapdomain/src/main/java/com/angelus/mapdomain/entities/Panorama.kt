package com.angelus.mapdomain.entities

import com.angelus.gamedomain.entities.Position

class Panorama(
  val tiles:List<List<Tile>> ) {
    fun getSimpleGrid(): Array<IntArray> {
        for (row in tiles) {
            row.map { }
        }
       return tiles.map {
           it.map {
               if(it.type.isWall) { 1 } else { 0}
           }.toIntArray()
       }.toTypedArray()
    }

    fun getPositionInSimpleGrid(): Position {
        val  grid = getSimpleGrid()
        return Position(
            grid[0].size/2,
                grid.size -1
        )
    }
}
