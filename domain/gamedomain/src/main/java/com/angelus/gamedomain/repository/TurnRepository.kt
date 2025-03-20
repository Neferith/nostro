package com.angelus.gamedomain.repository

import com.angelus.gamedomain.entities.Turn
import kotlinx.coroutines.flow.Flow

interface TurnRepository {
    fun observeTurn(): Flow<Turn>
    fun nextTurn()
}