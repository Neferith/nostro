package com.angelus.gamedomain.repository

import com.angelus.gamedomain.entities.BackgroundType

interface CurrentModuleRepository {
    fun getAllBackgroundStories(): List<BackgroundType>
}