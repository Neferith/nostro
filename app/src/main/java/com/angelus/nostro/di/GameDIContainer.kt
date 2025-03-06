package com.angelus.nostro.di

import com.angelus.nostro.page.game.GameScreenPageFactory

data class GameParams(val gamename: String)


class GameDIContainer(val params: GameParams): GameScreenPageFactory {
    // Initialise le DomainDIContainer de manière lazy (lorsqu'il est utilisé pour la première fois)
    private val domainDIContainer: GameDomainDIContainer by lazy {
        GameDomainDIContainer(params) // Exemple avec PlayerRepositoryImpl et PlayerDataSource
    }

    // Accès aux use cases via domainDIContainer
    override val playerUseCaseFactory get() = domainDIContainer
    override val currentMapUseCaseFactory get() = domainDIContainer
    override val gameUseCaseFactory get() = domainDIContainer
}