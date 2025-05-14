package com.angelus.gamedomain.repository

import com.angelus.gamedomain.entities.character.BackgroundType
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.item.Item

interface CurrentModuleRepository {
    fun getAllBackgroundStories(): List<BackgroundType>
    fun getStartPosition(): EntityPosition
    fun fetchItemsById(ids: Array<String>): Result<List<Item>>
}