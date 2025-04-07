package com.angelus.gamedomain.repository

import com.angelus.gamedomain.entities.GameSlot

interface GameSlotRepository {
    suspend fun fetchGameSlot(): Result<List<GameSlot>>
}