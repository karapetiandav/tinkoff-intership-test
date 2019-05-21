package ru.karapetiandav.tinkoffintership.features.news.presenters

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ru.karapetiandav.tinkoffintership.DependencyInjector
import ru.karapetiandav.tinkoffintership.features.news.contract.NewsContract
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository
import ru.karapetiandav.tinkoffintership.features.news.ui.state.Data
import ru.karapetiandav.tinkoffintership.features.news.ui.state.Error
import ru.karapetiandav.tinkoffintership.features.news.ui.state.Loading
import ru.karapetiandav.tinkoffintership.features.news.ui.state.NewsViewState

class NewsPresenter(dependencyInjector: DependencyInjector) : NewsContract.Presenter {

    override val state: PublishSubject<NewsViewState> = PublishSubject.create()

    private val newsRepository: NewsRepository = dependencyInjector.newsRepository()

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onViewCreated() {
        loadNews()
    }

    private fun loadNews() {
        val newsQuery = newsRepository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .map<NewsViewState>(::Data)
            .startWith(Loading)
            .onErrorReturn(::Error)
            .subscribe(state::onNext) { th -> Log.e("LOG_NEWS_PRESENTER", "ERROR", th) }

        compositeDisposable.add(newsQuery)
    }

    override fun onNewsClick() {

    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }


}