package ru.karapetiandav.tinkoffintership.features.news.ui.state

import ru.karapetiandav.tinkoffintership.features.news.models.News

sealed class NewsDetailsViewState
class NewsDetailsDate(val news: News): NewsDetailsViewState()
class NewsDetailsError(val error: Throwable): NewsDetailsViewState()