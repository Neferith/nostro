package com.angelus.gamedata.repository

import com.angelus.gamedomain.entities.BackgroundType
import com.angelus.gamedomain.entities.Module
import com.angelus.gamedomain.repository.CurrentGameRepository

class CurrentGameRepositoryImpl(val module: Module): CurrentGameRepository {
    override fun getAllBackgroundStories(): List<BackgroundType> {
        return module.backgrounds
    }
}