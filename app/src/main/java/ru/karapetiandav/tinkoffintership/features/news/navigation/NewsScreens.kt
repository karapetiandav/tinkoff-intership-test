package ru.karapetiandav.tinkoffintership.features.news.navigation

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

    object DetailsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return NewsDetailsFragment()
        }
    }
}