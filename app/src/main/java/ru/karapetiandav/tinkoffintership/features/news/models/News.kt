package ru.karapetiandav.tinkoffintership.features.news.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity
@TypeConverters(NewsDateConverter::class)
class News(
    @PrimaryKey
    val id: Int,
    val name: String,
    val text: String,
    val publicationDate: NewsDate,
    val bankInfoTypeId: Int
)

@Entity
class NewsDate(val milliseconds: Long)

class NewsDateConverter {
    @TypeConverter
    fun fromDate(newsDate: NewsDate): Long {
        return newsDate.milliseconds
    }

    @TypeConverter
    fun toDate(milliseconds: Long): NewsDate {
        return NewsDate(milliseconds)
    }
}