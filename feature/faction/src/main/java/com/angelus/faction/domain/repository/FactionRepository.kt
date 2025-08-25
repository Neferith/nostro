package com.angelus.faction.domain.repository

import com.angelus.faction.domain.entities.Faction

interface FactionRepository {
    suspend fun fetchFaction(factionId: String): Result<Faction>
}