package ru.karapetiandav.tinkoffintership.features.news.presenters

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ru.karapetiandav.tinkoffintership.DependencyInjector
import ru.karapetiandav.tinkoffintership.features.news.contract.NewsContract
import ru.karapetiandav.tinkoffintership.features.news.navigation.NewsScreens
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository
import ru.karapetiandav.tinkoffintership.features.news.ui.state.Data
import ru.karapetiandav.tinkoffintership.features.news.ui.state.Error
import ru.karapetiandav.tinkoffintership.features.news.ui.state.Loading
import ru.karapetiandav.tinkoffintership.features.news.ui.state.NewsViewState
import ru.terrakok.cicerone.Router

class NewsPresenter(dependencyInjector: DependencyInjector) : NewsContract.Presenter {

    override val state: PublishSubject<NewsViewState> = PublishSubject.create()

    private val newsRepository: NewsRepository = dependencyInjector.newsRepository()

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    private val router: Router = dependencyInjector.router()

    override fun onViewCreated() {
        loadNews()
    }

    private fun loadNews() {
        val newsQuery = newsRepository.getNews()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { news -> news.sortedBy { it.publicationDate.milliseconds } }
            .map<NewsViewState> { news -> Data(news) { onNewsClick() } }
            .startWith(Loading)
            .onErrorReturn(::Error)
            .subscribe(state::onNext) { th -> Log.e("LOG_NEWS_PRESENTER", "ERROR", th) }

        compositeDisposable.add(newsQuery)
    }

    override fun onNewsClick() {
        router.navigateTo(NewsScreens.DetailsScreen)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }


}