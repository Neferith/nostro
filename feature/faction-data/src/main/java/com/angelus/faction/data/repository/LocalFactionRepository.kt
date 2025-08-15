package com.angelus.faction.data.repository

import com.angelus.faction.data.FactionDatasource
import com.angelus.faction.domain.entities.Faction
import com.angelus.faction.domain.repository.FactionRepository

class FactionNotFoundException : Exception()

class LocalFactionRepository(private val datasource: FactionDatasource): FactionRepository {
    override suspend fun fetchFaction(factionId: String): Result<Faction> {
        val faction =  datasource.fetchFaction(factionId)
        return if(faction != null) {
            Result.success(faction)
        } else {
            Result.failure(FactionNotFoundException())
        }
    }
}