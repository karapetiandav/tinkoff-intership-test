package ru.karapetiandav.tinkoffintership

import ru.karapetiandav.tinkoffintership.features.news.network.NetworkService
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepositoryImpl
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

object DependencyInjectorImpl : DependencyInjector {

    private val networkService by lazy { NetworkService() }

    override fun networkService(): NetworkService = networkService

    override fun newsRepository(): NewsRepository = NewsRepositoryImpl()

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    override fun navigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    override fun router(): Router = cicerone.router
}