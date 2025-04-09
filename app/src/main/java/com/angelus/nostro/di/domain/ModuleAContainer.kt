package com.angelus.nostro.di.domain

import com.angelus.gamedomain.entities.Module
import com.angelus.mapdomain.entities.GameMap
import com.angelus.modulea.ModuleA

class ModuleAContainer {

    private val moduleA = ModuleA

    fun getModule(): Module {
        return ModuleA
    }

    fun getMaps(): Map<String,GameMap> {
        return moduleA.getAllMaps()
    }
}