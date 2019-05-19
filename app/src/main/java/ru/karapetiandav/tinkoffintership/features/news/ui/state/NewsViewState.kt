package ru.karapetiandav.tinkoffintership.features.news.ui.state

import ru.karapetiandav.tinkoffintership.ui.models.News

sealed class NewsViewState
data class Data(val news: List<News>) : NewsViewState()
object Loading : NewsViewState()
data class Error(val error: Throwable) : NewsViewState()