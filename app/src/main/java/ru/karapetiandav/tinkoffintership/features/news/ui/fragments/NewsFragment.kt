package ru.karapetiandav.tinkoffintership.features.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.redmadrobot.lib.sd.LoadingStateDelegate
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.android.ext.android.inject
import ru.karapetiandav.tinkoffintership.R
import ru.karapetiandav.tinkoffintership.features.news.models.News
import ru.karapetiandav.tinkoffintership.features.news.presenters.NewsViewModel
import ru.karapetiandav.tinkoffintership.features.news.presenters.NewsViewModelFactory
import ru.karapetiandav.tinkoffintership.features.news.repo.NewsRepository
import ru.karapetiandav.tinkoffintership.features.news.ui.adapters.NewsAdapter
import ru.karapetiandav.tinkoffintership.features.news.ui.state.*
import ru.karapetiandav.tinkoffintership.lifecycle.observe
import ru.terrakok.cicerone.Router

class NewsFragment : Fragment() {

    private lateinit var screenState: LoadingStateDelegate

    private lateinit var viewModelFactory: NewsViewModelFactory
    private lateinit var viewModel: NewsViewModel

    private val newsRepository: NewsRepository by inject()
    private val router: Router by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = NewsViewModelFactory(newsRepository, router)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
        viewModel.onViewCreated()

        screenState = LoadingStateDelegate(newsList, loading, zero_stub)

        observe(viewModel.state, ::onStateChanged)
    }

    private fun onStateChanged(state: NewsViewState) {
        when (state) {
            is Loading -> {
                screenState.showLoading()
            }
            is Data -> {
                screenState.showContent()
                displayNews(state.news) { viewModel.onNewsClick(it) }
            }
            is Error -> {
                screenState.showStub(
                    StubStateData(titleResId = state.error.localizedMessage)
                )
            }
        }
    }

    private fun displayNews(news: List<News>, onClickAction: (Int) -> Unit) {
        newsList.layoutManager = LinearLayoutManager(activity)
        newsList.adapter = NewsAdapter(news, onClickAction)
    }

}
