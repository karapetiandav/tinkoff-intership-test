package ru.karapetiandav.tinkoffintership

import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepositoryImpl

class DependencyInjectorImpl : DependencyInjector {
    override fun newsRepository(): NewsRepository {
        return NewsRepositoryImpl()
    }
}