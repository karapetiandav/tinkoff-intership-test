package ru.karapetiandav.tinkoffintership

import android.content.Context
import ru.karapetiandav.tinkoffintership.database.AppDatabase
import ru.karapetiandav.tinkoffintership.features.news.network.NetworkService
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepositoryImpl
import ru.karapetiandav.tinkoffintership.services.ConnectivityManager
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class DependencyInjectorImpl private constructor(private val context: Context) : DependencyInjector {

    companion object {

        @Volatile
        private var INSTANCE: DependencyInjector? = null

        fun getInstance(context: Context): DependencyInjector = INSTANCE ?: synchronized(this) {
            INSTANCE ?: DependencyInjectorImpl(context).also { INSTANCE = it }
        }
    }

    private val networkService by lazy { NetworkService.getInstance() }

    override fun networkService(): NetworkService = networkService

    override fun newsRepository(): NewsRepository = NewsRepositoryImpl(this)

    private val cicerone: Cicerone<Router> = Cicerone.create()

    override fun navigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    override fun router(): Router = cicerone.router

    override fun database(): AppDatabase = AppDatabase.getInstance(context)

    override fun connectivityManager(): ConnectivityManager = ConnectivityManager(context)
}