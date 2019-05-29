package ru.karapetiandav.tinkoffintership.features.news.presenters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.karapetiandav.tinkoffintership.DependencyInjector
import ru.karapetiandav.tinkoffintership.contract.BaseViewModel
import ru.karapetiandav.tinkoffintership.features.news.navigation.NewsScreens
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository
import ru.karapetiandav.tinkoffintership.features.news.ui.state.Data
import ru.karapetiandav.tinkoffintership.features.news.ui.state.Error
import ru.karapetiandav.tinkoffintership.features.news.ui.state.Loading
import ru.karapetiandav.tinkoffintership.features.news.ui.state.NewsViewState
import ru.karapetiandav.tinkoffintership.lifecycle.onNext
import ru.terrakok.cicerone.Router

class NewsViewModel(dependencyInjector: DependencyInjector) : BaseViewModel() {

    private val _state = MutableLiveData<NewsViewState>()
    val state: LiveData<NewsViewState>
        get() = _state

    private val newsRepository: NewsRepository = dependencyInjector.newsRepository()

    private val router: Router = dependencyInjector.router()

    private fun loadNews() {
        newsRepository.getNews()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<NewsViewState> { news -> Data(news) }
            .startWith(Loading)
            .onErrorReturn(::Error)
            .subscribe(_state::onNext) { th -> Log.e("LOG_NEWS_PRESENTER", "ERROR", th) }
            .disposeOnViewModelDestroy()
    }

    fun onNewsClick(newsId: Int) {
        router.navigateTo(NewsScreens.DetailsScreen(newsId))
    }

    fun onViewCreated() {
        loadNews()
    }
}

class NewsViewModelFactory(private val dependencyInjector: DependencyInjector) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(dependencyInjector) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}