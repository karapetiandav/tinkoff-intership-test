package ru.karapetiandav.tinkoffintership.features.news.repo

import io.reactivex.Single
import ru.karapetiandav.tinkoffintership.features.news.models.News

interface NewsRepository {
    fun getNews(): Single<List<News>>
}