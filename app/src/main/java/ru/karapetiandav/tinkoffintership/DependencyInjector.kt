package ru.karapetiandav.tinkoffintership

import ru.karapetiandav.tinkoffintership.database.AppDatabase
import ru.karapetiandav.tinkoffintership.features.news.network.NetworkService
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository
import ru.karapetiandav.tinkoffintership.services.ConnectivityManager
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

interface DependencyInjector {
    fun newsRepository(): NewsRepository
    fun networkService(): NetworkService
    fun database(): AppDatabase
    fun navigatorHolder(): NavigatorHolder
    fun router(): Router
    fun connectivityManager(): ConnectivityManager
}