package com.angelus.data.gamedata

import com.angelus.gamedata.data.dto.AttributeDTO
import com.angelus.gamedata.data.dto.AttributeRequirementDTO
import com.angelus.gamedata.data.dto.AttributesDTO
import com.angelus.gamedata.data.dto.AttributesModifierDTO
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test

class AttributesDTOTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `test AttributesModifierDTO serialization and deserialization`() {
        val modifier = AttributesModifierDTO(musculature = 10, flexibility = 8, brain = 15, vitality = 12)
        val serialized = json.encodeToString(modifier)
        val deserialized = json.decodeFromString<AttributesModifierDTO>(serialized)

        assertEquals(modifier, deserialized)
    }

    @Test
    fun `test AttributeRequirementDTO serialization with nullable values`() {
        val requirement = AttributeRequirementDTO(musculature = 10, brain = 5)
        val serialized = json.encodeToString(requirement)
        val deserialized = json.decodeFromString<AttributeRequirementDTO>(serialized)

        assertEquals(requirement.musculature, deserialized.musculature)
        assertNull(deserialized.flexibility)
        assertEquals(requirement.brain, deserialized.brain)
    }

    @Test
    fun `test AttributeDTO creation`() {
        val attribute = AttributeDTO(permanent = 10, min = 5, max = 20)
        assertEquals(10, attribute.permanent)
        assertEquals(5, attribute.min)
        assertEquals(20, attribute.max)
    }

    @Test
    fun `test AttributesDTO serialization and deserialization`() {
        val attributes = AttributesDTO(
            musculature = AttributeDTO(10, 5, 20),
            flexibility = AttributeDTO(8, 4, 16),
            brain = AttributeDTO(15, 7, 25),
            vitality = AttributeDTO(12, 6, 18)
        )
        val serialized = json.encodeToString(attributes)
        val deserialized = json.decodeFromString<AttributesDTO>(serialized)

        assertEquals(attributes, deserialized)
    }
}
