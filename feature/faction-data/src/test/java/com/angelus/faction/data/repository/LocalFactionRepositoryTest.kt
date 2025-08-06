package com.angelus.faction.data.repository

import com.angelus.faction.data.FactionDatasource
import com.angelus.faction.domain.entities.Faction
import com.angelus.faction.domain.repository.FactionRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Test


class MockFactionDataSource: FactionDatasource {
    override suspend fun fetchFaction(factionId: String): Faction? {
        if(factionId == "MONSTER") {
            return Faction(
                id = "MONSTER",
                name = "MONSTER",
                description = "MONSTER",
                relations = emptyMap()
            )
        }
        return null
    }

    override suspend fun updateFaction(faction: Faction) {}

}

class LocalFactionRepositoryTest {

    @Test
    fun ` LocalFactionRepositoryTest return faction by id`() = runTest {


        val repository: FactionRepository = LocalFactionRepository(MockFactionDataSource())
        val monsterFaction = "MONSTER"

        val hostility: Faction? = repository.fetchFaction(monsterFaction).getOrNull()
        assertNotNull(hostility)
    }
}