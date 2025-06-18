package com.angelus.faction.entities

import com.angelus.faction.domain.entities.Faction
import com.angelus.faction.domain.entities.Relation
import org.junit.Assert.assertTrue
import org.junit.Test

class FactionTest {

    @Test
    fun checkHostility() {
        val factionMonsterId = "MONSTER"
        val faction1 = Faction(
            id = Faction.PLAYER_FACTION_ID,
            name = "PLAYER",
            description = "¨PLAYER",
            relations = emptyMap()
        )
        val faction2 = Faction(
            id = factionMonsterId,
            name = "MONSTER",
            description = "¨MONSTER",
            relations = emptyMap()
        )

        faction2.setRelation(Faction.PLAYER_FACTION_ID, 0)

        assertTrue(faction1.checkHostility(Faction.PLAYER_FACTION_ID) == Relation.HOSTILE)
    }
}