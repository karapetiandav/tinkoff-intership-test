package ru.karapetiandav.tinkoffintership

import ru.karapetiandav.tinkoffintership.features.news.network.NetworkService
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

interface DependencyInjector {
    fun newsRepository(): NewsRepository
    fun networkService(): NetworkService
    fun navigatorHolder(): NavigatorHolder
    fun router(): Router
}