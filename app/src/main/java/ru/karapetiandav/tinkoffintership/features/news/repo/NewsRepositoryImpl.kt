package ru.karapetiandav.tinkoffintership.features.news.repo

import io.reactivex.Single
import ru.karapetiandav.tinkoffintership.ui.models.News
import ru.karapetiandav.tinkoffintership.ui.models.NewsDate

class NewsRepositoryImpl : NewsRepository {
    override fun getNews(): Single<List<News>> {
        val fakeNews = (0..50).mapIndexed { index, i -> News(index, "News #$index", "Text #$index", NewsDate(0), 0) }
        return Single.just(fakeNews)
    }
}