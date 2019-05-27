package ru.karapetiandav.tinkoffintership.features.news.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.karapetiandav.tinkoffintership.DependencyInjector

class NewsDetailsViewModelFactory(private val newsId: Int, private val dependencyInjector: DependencyInjector) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)) {
            return NewsDetailsViewModel(newsId, dependencyInjector) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}