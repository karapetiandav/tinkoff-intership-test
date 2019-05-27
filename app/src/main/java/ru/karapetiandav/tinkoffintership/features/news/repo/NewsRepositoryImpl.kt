package ru.karapetiandav.tinkoffintership.features.news.repo

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.karapetiandav.tinkoffintership.features.news.models.News
import ru.karapetiandav.tinkoffintership.features.news.network.NetworkService

class NewsRepositoryImpl : NewsRepository {
    override fun getNews(): Single<List<News>> {
        return NetworkService().getTinkoffApi().getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { response -> response.payload }
    }
}