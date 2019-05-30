package ru.karapetiandav.tinkoffintership.features.news.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.karapetiandav.tinkoffintership.database.AppDatabase
import ru.terrakok.cicerone.Router

class NewsDetailsViewModelFactory(private val newsId: Int, private val database: AppDatabase, private val router: Router) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)) {
            return NewsDetailsViewModel(newsId, database, router) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}