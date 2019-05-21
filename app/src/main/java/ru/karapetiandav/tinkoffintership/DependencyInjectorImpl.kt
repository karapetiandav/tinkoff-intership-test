package ru.karapetiandav.tinkoffintership

import ru.karapetiandav.tinkoffintership.features.news.network.NetworkService
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepositoryImpl

object DependencyInjectorImpl : DependencyInjector {

    private val networkService by lazy { NetworkService() }

    override fun networkService(): NetworkService {
        return networkService
    }

    override fun newsRepository(): NewsRepository {
        return NewsRepositoryImpl()
    }
}