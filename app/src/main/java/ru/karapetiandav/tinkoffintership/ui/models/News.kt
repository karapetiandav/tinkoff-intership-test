package ru.karapetiandav.tinkoffintership.ui.models

data class News(
    val id: Int,
    val name: String,
    val text: String,
    val publicationDate: NewsDate,
    val bankInfoTypeId: Int
)

data class NewsDate(val milliseconds: Long)
