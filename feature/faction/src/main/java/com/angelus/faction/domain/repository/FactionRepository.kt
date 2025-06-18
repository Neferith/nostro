package com.angelus.faction.domain.repository

import com.angelus.faction.domain.entities.Faction

interface FactionRepository {
    fun fetchFaction(factionId: String): Faction?
}