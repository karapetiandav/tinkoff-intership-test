package ru.karapetiandav.tinkoffintership.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.karapetiandav.tinkoffintership.database.dao.NewsDao
import ru.karapetiandav.tinkoffintership.features.news.models.News

@Suppress("ReplaceArrayOfWithLiteral")
@Database(entities = arrayOf(News::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}