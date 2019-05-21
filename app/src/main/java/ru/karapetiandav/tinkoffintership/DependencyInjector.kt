package ru.karapetiandav.tinkoffintership

import ru.karapetiandav.tinkoffintership.features.news.network.NetworkService
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository

interface DependencyInjector {
    fun newsRepository(): NewsRepository
    fun networkService(): NetworkService
}