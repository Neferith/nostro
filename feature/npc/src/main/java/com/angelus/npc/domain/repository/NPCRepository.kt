package com.angelus.npc.domain.repository

import com.angelus.npc.domain.entities.NPC

interface NPCRepository {
    fun fetchNPC(characterId: String): Result<NPC>
    fun updateNPC(npc: NPC): Result<NPC>
}