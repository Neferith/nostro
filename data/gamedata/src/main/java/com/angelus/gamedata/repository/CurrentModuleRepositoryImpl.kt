package com.angelus.gamedata.repository

import com.angelus.gamedomain.entities.BackgroundType
import com.angelus.gamedomain.entities.Module
import com.angelus.gamedomain.repository.CurrentModuleRepository

class CurrentModuleRepositoryImpl(val module: Module): CurrentModuleRepository {
    override fun getAllBackgroundStories(): List<BackgroundType> {
        return module.backgrounds
    }
}