package ru.karapetiandav.tinkoffintership.features.news.ui.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.redmadrobot.lib.sd.LoadingStateDelegate
import kotlinx.android.synthetic.main.fragment_news_details.*
import org.joda.time.DateTime
import ru.karapetiandav.tinkoffintership.DependencyInjectorImpl
import ru.karapetiandav.tinkoffintership.R
import ru.karapetiandav.tinkoffintership.features.news.ui.state.NewsDetailsDate
import ru.karapetiandav.tinkoffintership.features.news.ui.state.NewsDetailsError
import ru.karapetiandav.tinkoffintership.features.news.ui.state.NewsDetailsViewState
import ru.karapetiandav.tinkoffintership.features.news.ui.state.StubStateData
import ru.karapetiandav.tinkoffintership.features.news.ui.viewmodel.NewsDetailsViewModel
import ru.karapetiandav.tinkoffintership.features.news.ui.viewmodel.NewsDetailsViewModelFactory

class NewsDetailsFragment : Fragment() {

    private lateinit var screenState: LoadingStateDelegate

    private lateinit var viewModelFactory: NewsDetailsViewModelFactory
    private lateinit var viewModel: NewsDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context: Context = context ?: return

        val newsId: Int = arguments?.getInt("newsId") ?: throw IllegalArgumentException("In bundle must be news")
        viewModelFactory = NewsDetailsViewModelFactory(newsId, DependencyInjectorImpl.getInstance(context))
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsDetailsViewModel::class.java)

        viewModel.state.subscribe(::stateChanged)

        screenState = LoadingStateDelegate(contentView = content_group, stubView = details_stub)
    }

    private fun stateChanged(state: NewsDetailsViewState) {
        when (state) {
            is NewsDetailsDate -> {
                screenState.showContent()
                news_details_publication_date.text = DateTime(state.news.publicationDate.milliseconds).toString("dd.MM.yyyy")
                news_details_text.text = state.news.text
            }
            is NewsDetailsError -> {
                screenState.showStub(StubStateData(titleResId = state.error.localizedMessage))
            }
        }
    }

}
