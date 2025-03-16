package com.angelus.gamedomain.repository

import com.angelus.gamedomain.entities.BackgroundType
import com.angelus.gamedomain.entities.Module

interface CurrentGameRepository {
    fun getAllBackgroundStories(): List<BackgroundType>
}