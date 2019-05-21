package ru.karapetiandav.tinkoffintership.features.news.contract

import io.reactivex.subjects.PublishSubject
import ru.karapetiandav.tinkoffintership.contract.BasePresenter
import ru.karapetiandav.tinkoffintership.contract.BaseView
import ru.karapetiandav.tinkoffintership.features.news.ui.state.NewsViewState
import ru.karapetiandav.tinkoffintership.ui.models.News

interface NewsContract {
    interface Presenter : BasePresenter {
        val state: PublishSubject<NewsViewState>
        fun onViewCreated()
        fun onNewsClick()
    }

    interface View : BaseView<Presenter> {
        fun displayNews(news: List<News>, onClickAction: (Int) -> Unit)
    }
}