package com.angelus.npc.domain.entities

import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Orientation
import com.angelus.gamedomain.entities.Position
import com.angelus.npc.domain.mock.characterMock1
import com.angelus.npc.domain.mock.characterMock2
import com.angelus.npc.domain.mock.mockCharacterId1
import com.angelus.npc.domain.mock.mockCharacterPosition
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TurnListTest {

    private val npc1 = TurnType.NPC(
        character = characterMock1,
        entityPosition = EntityPosition(
            "MAP", mockCharacterPosition,
            orientation = Orientation.SOUTH
        )
    )

    private val npc2 = TurnType.NPC(
        character = characterMock2,
        entityPosition = EntityPosition(
            "MAP", Position(1, 1),
            orientation = Orientation.SOUTH
        )
    )

    private val player1 = TurnType.PLAYER(id = "player1")

    private val turnList = TurnList(
        turns = listOf(
            Turn(player1),
            Turn(npc1),
            Turn(npc2)
        ),
        currentTurn = 0
    )

    @Test
    fun `nextTurn should advance to next turn`() {
        val next = turnList.nextTurn()
        assertEquals(1, next.currentTurn)
        assertEquals(npc1, next.current.type)
    }

    @Test
    fun `nextTurn should wrap around to zero`() {
        val last = turnList.copy(currentTurn = 2)
        val next = last.nextTurn()
        assertEquals(0, next.currentTurn)
        assertEquals(player1, next.current.type)
    }

    @Test
    fun `npcTurnsAtPositions should return NPCs at given positions`() {
        val result = turnList.npcTurnsAtPositions(listOf(Position(0, 0), mockCharacterPosition))
        assertEquals(1, result.size)
        assertEquals(mockCharacterId1, result[0].character.id)
    }

    @Test
    fun `npcTurnsAtPositions should return empty list if no NPCs at positions`() {
        val result = turnList.npcTurnsAtPositions(listOf(Position(9, 9)))
        assertTrue(result.isEmpty())
    }

    @Test
    fun `npcTurnByCharacterId should return correct NPC`() {
        val result = turnList.npcTurnByCharacterId(mockCharacterId1)
        assertNotNull(result)
        assertEquals(mockCharacterId1, result?.character?.id)
    }

    @Test
    fun `npcTurnByCharacterId should return null if not found`() {
        val result = turnList.npcTurnByCharacterId("unknown")
        assertNull(result)
    }
}
