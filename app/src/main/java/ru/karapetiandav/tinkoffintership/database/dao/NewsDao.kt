package ru.karapetiandav.tinkoffintership.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import io.reactivex.Single
import ru.karapetiandav.tinkoffintership.features.news.models.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<News>)

    @Query("SELECT * FROM News")
    fun getAll(): Single<List<News>>

    @Query("SELECT * from News WHERE id = :id")
    fun findById(id: Int): Maybe<News>
}