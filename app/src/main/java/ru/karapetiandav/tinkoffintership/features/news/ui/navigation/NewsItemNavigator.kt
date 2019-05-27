package ru.karapetiandav.tinkoffintership.features.news.ui.navigation

import ru.karapetiandav.tinkoffintership.features.news.models.News

interface NewsItemNavigator {
    fun openNewsDetail(news: News)
}