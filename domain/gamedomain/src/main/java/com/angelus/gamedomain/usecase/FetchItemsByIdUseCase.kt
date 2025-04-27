package com.angelus.gamedomain.usecase

import com.angelus.gamedomain.entities.GameSlot
import com.angelus.gamedomain.entities.item.Item
import com.angelus.gamedomain.repository.CurrentModuleRepository
import com.angelus.gamedomain.repository.GameSlotRepository

interface FetchItemsByIdUseCase {
    operator suspend fun invoke(ids:Array<String>): Result<List<Item>>
}

class FetchItemsByIdUseCaseImpl(val repository: CurrentModuleRepository): FetchItemsByIdUseCase {
    override suspend fun invoke(ids: Array<String>): Result<List<Item>> {
        return repository.fetchItemsById(ids)
    }

}