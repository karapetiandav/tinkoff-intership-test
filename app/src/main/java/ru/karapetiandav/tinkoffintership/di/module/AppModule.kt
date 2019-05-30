package ru.karapetiandav.tinkoffintership.di.module

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module
import ru.karapetiandav.tinkoffintership.database.AppDatabase
import ru.karapetiandav.tinkoffintership.features.news.network.NetworkService
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepositoryImpl
import ru.karapetiandav.tinkoffintership.services.ConnectivityManager
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

object Modules {
    val appModule = module {
        single { NetworkService() }
        single { ConnectivityManager(get()) }
        single { buildDatabase(get()) }

        single { Cicerone.create() }
        single { get<Cicerone<Router>>().router }
        single { get<Cicerone<Router>>().navigatorHolder }

        factory<NewsRepository> { NewsRepositoryImpl(get(), get(), get()) }
    }

    private fun buildDatabase(context: Context) = Room.inMemoryDatabaseBuilder(
        context.applicationContext,
        AppDatabase::class.java
    ).build()
}