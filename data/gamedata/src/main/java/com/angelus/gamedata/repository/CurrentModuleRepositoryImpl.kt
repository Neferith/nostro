package com.angelus.gamedata.repository

import com.angelus.gamedomain.entities.character.BackgroundType
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Module
import com.angelus.gamedomain.entities.item.Item
import com.angelus.gamedomain.entities.item.ItemRegistry
import com.angelus.gamedomain.repository.CurrentModuleRepository

class CurrentModuleRepositoryImpl(val module: Module): CurrentModuleRepository {
    override fun getAllBackgroundStories(): List<BackgroundType> {
        return module.backgrounds
    }

    override fun getStartPosition(): EntityPosition {
        return module.startPosition
    }

    override fun fetchItemsById(ids: Array<String>): Result<List<Item>> {
        return Result.success(ItemRegistry.getItemsByIds(ids.toList()))
    }
}