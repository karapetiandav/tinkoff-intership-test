package ru.karapetiandav.tinkoffintership

import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository

interface DependencyInjector {
    fun newsRepository(): NewsRepository
}