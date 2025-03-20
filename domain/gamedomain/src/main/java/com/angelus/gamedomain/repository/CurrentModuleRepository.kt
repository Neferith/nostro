package com.angelus.gamedomain.repository

import com.angelus.gamedomain.entities.BackgroundType
import com.angelus.gamedomain.entities.EntityPosition

interface CurrentModuleRepository {
    fun getAllBackgroundStories(): List<BackgroundType>
    fun getStartPosition(): EntityPosition
}