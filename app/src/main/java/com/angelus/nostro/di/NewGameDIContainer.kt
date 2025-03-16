package com.angelus.nostro.di

import com.angelus.gamedata.repository.CurrentGameRepositoryImpl
import com.angelus.gamedomain.factory.CurrentGameUseCaseFactory
import com.angelus.gamedomain.repository.CurrentGameRepository
import com.angelus.modulea.ModuleA
import com.angelus.nostro.page.newgame.NewGamePageFactory

class NewGameDIContainer: CurrentGameUseCaseFactory{

    override val currentGameRepository: CurrentGameRepository = CurrentGameRepositoryImpl(ModuleA())



}