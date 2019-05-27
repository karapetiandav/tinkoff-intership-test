package ru.karapetiandav.tinkoffintership.features.news.ui.viewmodel

import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ru.karapetiandav.tinkoffintership.DependencyInjector
import ru.karapetiandav.tinkoffintership.contract.BaseViewModel
import ru.karapetiandav.tinkoffintership.features.news.models.News
import ru.karapetiandav.tinkoffintership.features.news.ui.state.NewsDetailsDate
import ru.karapetiandav.tinkoffintership.features.news.ui.state.NewsDetailsViewState

class NewsDetailsViewModel(private val newsId: Int, dependencyInjector: DependencyInjector) : BaseViewModel() {

    private lateinit var currentNews: News

    private val database = dependencyInjector.database()

    val state: PublishSubject<NewsDetailsViewState> = PublishSubject.create()

    init {
        database.newsDao().findById(newsId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(::showNews)
            .disposeOnViewModelDestroy()
    }

    private fun showNews(news: News) {
        state.onNext(NewsDetailsDate(news))
    }

}