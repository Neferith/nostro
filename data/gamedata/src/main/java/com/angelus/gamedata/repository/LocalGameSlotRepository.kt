package com.angelus.gamedata.repository

import com.angelus.gamedata.data.GameSlotDataSource
import com.angelus.gamedomain.entities.GameSlot
import com.angelus.gamedomain.repository.GameSlotRepository

class LocalGameSlotRepository(val dataSource: GameSlotDataSource): GameSlotRepository {
    override suspend fun fetchGameSlot(): Result<List<GameSlot>> {
        return dataSource.fetchGameSlot()
    }
}