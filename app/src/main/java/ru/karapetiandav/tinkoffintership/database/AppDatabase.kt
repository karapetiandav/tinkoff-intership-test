package ru.karapetiandav.tinkoffintership.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.karapetiandav.tinkoffintership.database.dao.NewsDao
import ru.karapetiandav.tinkoffintership.features.news.models.News

@Suppress("ReplaceArrayOfWithLiteral")
@Database(entities = arrayOf(News::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.inMemoryDatabaseBuilder(
            context.applicationContext,
            AppDatabase::class.java
        ).build()
    }

    abstract fun newsDao(): NewsDao
}