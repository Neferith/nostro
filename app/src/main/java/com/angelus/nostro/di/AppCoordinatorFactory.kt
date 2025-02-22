package com.angelus.nostro.di

import com.angelus.nostro.page.game.GameScreenPageFactory

class AppCoordinatorFactory() : GameScreenPageFactory {
    // Initialise le DomainDIContainer de manière lazy (lorsqu'il est utilisé pour la première fois)
    private val domainDIContainer: DomainDIContainer by lazy {
        DomainDIContainer() // Exemple avec PlayerRepositoryImpl et PlayerDataSource
    }

    // Accès aux use cases via domainDIContainer
    override val playerUseCaseFactory get() = domainDIContainer
}