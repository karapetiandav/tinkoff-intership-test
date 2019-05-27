package ru.karapetiandav.tinkoffintership.features.news.navigation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.karapetiandav.tinkoffintership.features.news.ui.fragments.NewsDetailsFragment
import ru.karapetiandav.tinkoffintership.features.news.ui.fragments.NewsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class NewsScreens {
    object NewsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return NewsFragment()
        }
    }

    class DetailsScreen(private val newsId: Int) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return NewsDetailsFragment().apply {
                arguments = bundleOf("newsId" to newsId)
            }
        }
    }
}