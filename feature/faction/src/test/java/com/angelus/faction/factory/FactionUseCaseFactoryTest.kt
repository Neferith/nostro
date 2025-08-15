package com.angelus.faction.factory

import com.angelus.faction.domain.CheckFactionUseCaseTest
import com.angelus.faction.domain.factory.FactionUseCasFactory
import com.angelus.faction.domain.repository.FactionRepository
import com.angelus.faction.domain.usecase.CheckFactionUseCase
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class FactionUseCaseFactoryTest {

    class MockFactory: FactionUseCasFactory {
        override val repository: FactionRepository = CheckFactionUseCaseTest.MockFactionRepository()
    }

    @Test
    fun `Instancier un use case CheckFactionUseCase`() {

        val factory: FactionUseCasFactory = MockFactory()
        val useCase: CheckFactionUseCase = factory.makeCheckFactionUseCase()
        assertNotNull(useCase)
    }
}