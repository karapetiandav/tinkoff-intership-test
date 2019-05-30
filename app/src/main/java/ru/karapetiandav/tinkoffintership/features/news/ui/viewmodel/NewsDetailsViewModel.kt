package ru.karapetiandav.tinkoffintership.features.news.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.karapetiandav.tinkoffintership.contract.BaseViewModel
import ru.karapetiandav.tinkoffintership.database.AppDatabase
import ru.karapetiandav.tinkoffintership.features.news.models.News
import ru.karapetiandav.tinkoffintership.features.news.navigation.NewsScreens
import ru.karapetiandav.tinkoffintership.features.news.ui.state.NewsDetailsDate
import ru.karapetiandav.tinkoffintership.features.news.ui.state.NewsDetailsViewState
import ru.karapetiandav.tinkoffintership.lifecycle.onNext
import ru.terrakok.cicerone.Router

class NewsDetailsViewModel(newsId: Int, database: AppDatabase, private val router: Router) : BaseViewModel() {

    private val _state = MutableLiveData<NewsDetailsViewState>()
    val state: LiveData<NewsDetailsViewState>
    get() = _state

    init {
        database.newsDao().findById(newsId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::showNews)
            .disposeOnViewModelDestroy()
    }

    private fun showNews(news: News) {
        _state.onNext(NewsDetailsDate(news))
    }

    fun onBackButtonPressed() {
        router.backTo(NewsScreens.NewsScreen)
    }

}