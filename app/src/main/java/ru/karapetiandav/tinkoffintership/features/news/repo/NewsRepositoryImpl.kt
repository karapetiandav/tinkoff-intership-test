package ru.karapetiandav.tinkoffintership.features.news.repo

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.karapetiandav.tinkoffintership.DependencyInjector
import ru.karapetiandav.tinkoffintership.database.AppDatabase
import ru.karapetiandav.tinkoffintership.features.news.models.News
import ru.karapetiandav.tinkoffintership.features.news.network.NetworkService
import ru.karapetiandav.tinkoffintership.services.ConnectivityManager

class NewsRepositoryImpl(dependencyInjector: DependencyInjector) : NewsRepository {

    private val connectivityManager: ConnectivityManager = dependencyInjector.connectivityManager()
    private val database: AppDatabase = dependencyInjector.database()
    private val networkService: NetworkService = dependencyInjector.networkService()

    private var newsCached = false

    override fun getNews(): Single<List<News>> {
        return if (connectivityManager.hasNetwork() && !newsCached) {
            networkService.getTinkoffApi().getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { response -> response.payload }
                .map { news ->
                    database.newsDao().insertNews(news).also { newsCached = true }
                    val outputNews = news.sortedBy { it.publicationDate.milliseconds }
                    outputNews
                }
        } else {
            database.newsDao().getAll()
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
                .map { news -> news.sortedBy { it.publicationDate.milliseconds } }
        }
    }

    override fun getNewsById(id: Int): Maybe<News> {
        return database.newsDao().findById(id)
    }
}