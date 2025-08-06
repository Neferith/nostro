package com.angelus.faction.domain.factory

import com.angelus.faction.domain.repository.FactionRepository
import com.angelus.faction.domain.usecase.CheckFactionUseCase
import com.angelus.faction.domain.usecase.CheckFactionUseCaseImpl

interface FactionUseCasFactory {
    val repository: FactionRepository
    fun makeCheckFactionUseCase(): CheckFactionUseCase {
        return CheckFactionUseCaseImpl(repository)
    }
}