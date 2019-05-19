package ru.karapetiandav.tinkoffintership.features.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.redmadrobot.lib.sd.LoadingStateDelegate
import kotlinx.android.synthetic.main.fragment_news.*
import ru.karapetiandav.tinkoffintership.DependencyInjectorImpl
import ru.karapetiandav.tinkoffintership.R
import ru.karapetiandav.tinkoffintership.features.news.contract.NewsContract
import ru.karapetiandav.tinkoffintership.features.news.presenters.NewsPresenter
import ru.karapetiandav.tinkoffintership.features.news.ui.adapters.NewsAdapter
import ru.karapetiandav.tinkoffintership.features.news.ui.state.Data
import ru.karapetiandav.tinkoffintership.features.news.ui.state.Error
import ru.karapetiandav.tinkoffintership.features.news.ui.state.Loading
import ru.karapetiandav.tinkoffintership.features.news.ui.state.NewsViewState
import ru.karapetiandav.tinkoffintership.ui.models.News

class NewsFragment : Fragment(), NewsContract.View {

    private lateinit var screenState: LoadingStateDelegate

    private lateinit var presenter: NewsContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        screenState = LoadingStateDelegate(newsList, loading)

        setPresenter(NewsPresenter(DependencyInjectorImpl()))

        presenter.state.subscribe(::onStateChanged)
        presenter.onViewCreated()
    }

    override fun setPresenter(presenter: NewsContract.Presenter) {
        this.presenter = presenter
    }

    private fun onStateChanged(state: NewsViewState) {
        when (state) {
            is Loading -> {
                screenState.showLoading()
            }
            is Data -> {
                screenState.showContent()
                displayNews(state.news)
            }
            is Error -> {
                screenState.showStub()
            }
        }
    }

    override fun displayNews(news: List<News>) {
        newsList.layoutManager = LinearLayoutManager(activity)
        newsList.adapter = NewsAdapter(news)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}
