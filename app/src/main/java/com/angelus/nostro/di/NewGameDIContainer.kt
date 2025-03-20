package com.angelus.nostro.di

import com.angelus.gamedata.repository.CurrentModuleRepositoryImpl
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.gamedomain.repository.CurrentModuleRepository
import com.angelus.modulea.ModuleA

class NewGameDIContainer: CurrentGameUseCaseFactory{

    override val currentModuleRepository: CurrentModuleRepository = CurrentModuleRepositoryImpl(ModuleA())



}
