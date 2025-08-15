package com.angelus.faction.domain

import com.angelus.faction.domain.entities.Faction
import com.angelus.faction.domain.entities.Relation
import com.angelus.faction.domain.repository.FactionRepository
import com.angelus.faction.domain.usecase.CheckFactionUseCase
import com.angelus.faction.domain.usecase.CheckFactionUseCaseImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

val mockPlayerFaction = Faction(
    id = Faction.PLAYER_FACTION_ID,
    name = "PLAYER",
    description = "PLAYER",
    relations = emptyMap()
)

val mockMonsterFaction = Faction(
    id = "MONSTER",
    name = "MONSTER",
    description = "MONSTER",
    relations = mapOf(Faction.PLAYER_FACTION_ID to 0)
)


class CheckFactionUseCaseTest {



    class MockFactionRepository: FactionRepository {
        override suspend fun fetchFaction(factionId: String): Result<Faction> {
            if(factionId == Faction.PLAYER_FACTION_ID) {
                return Result.success(mockPlayerFaction)
            }

            if(factionId == "MONSTER") {
                return Result.success(mockMonsterFaction)
            }
            return Result.failure(Exception())
        }

    }


    @Test
    fun `Verifier le niveau d'hostilite des monstres envers le joueur`()  = runTest {
       val useCase: CheckFactionUseCase = CheckFactionUseCaseImpl(MockFactionRepository())
       val playerFaction: String = Faction.PLAYER_FACTION_ID
       val monsterFaction = "MONSTER"

        val hostility: Relation = useCase(monsterFaction, playerFaction)
        assertEquals(hostility, Relation.HOSTILE)
    }
}