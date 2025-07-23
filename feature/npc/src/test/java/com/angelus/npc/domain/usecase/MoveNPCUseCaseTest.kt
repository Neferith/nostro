package com.angelus.npc.domain.usecase

class MoveNPCUseCaseTest {
    


   /* class MockNPCRepository: NPCRepository {
        /* override suspend fun fetchFaction(factionId: String): Result<Faction> {
            if(factionId == Faction.PLAYER_FACTION_ID) {
                return Result.success(mockPlayerFaction)
            }

            if(factionId == "MONSTER") {
                return Result.success(mockMonsterFaction)
            }
            return Result.failure(Exception())
        }*/
        val mockCharacter = Character(
            id = "NPC_ID",
            factionId = TODO(),
            mainAttributes = TODO(),
            characterLevel = TODO(),
            description = TODO(),
            skills = TODO(),
            inventory = TODO()
        )
        override fun moveNPC(characterId: String, newPosition: EntityPosition): Result<Character> {
            if(characterId == "NPC_ID") {
                return Result.success(mockCharacter)
            }
            return Result.failure(Exception())
        }

    }


    @Test
    fun `Changer la position d'un NPC`()  = runTest {
        val useCase: MoveNPCUseCase = MoveNPCUseCaseImpl(MockNPCRepository())
        val npcId: String = "NPC_ID"
        val newPosition = EntityPosition(
            mapId = "MAP_ID",
            position = Position(0,1),
            orientation = Orientation.NORTH
        )
        val params =  MoveNPCUseCase.Params(
            characterId = npcId,
            newPosition = newPosition
        )

        val result = useCase(params)
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull()?.entityPosition?.position, newPosition)
    }*/
}