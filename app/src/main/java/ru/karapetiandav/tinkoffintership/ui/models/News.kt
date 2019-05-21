package ru.karapetiandav.tinkoffintership.ui.models

class News(
    val id: Int,
    val name: String,
    val text: String,
    val publicationDate: NewsDate,
    val bankInfoTypeId: Int
)

class NewsDate(val milliseconds: Long)