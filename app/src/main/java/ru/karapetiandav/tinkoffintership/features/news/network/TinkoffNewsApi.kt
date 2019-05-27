package ru.karapetiandav.tinkoffintership.features.news.network

import io.reactivex.Single
import retrofit2.http.GET
import ru.karapetiandav.tinkoffintership.features.news.models.NewsResponse

interface TinkoffNewsApi {
    @GET("news")
    fun getNews(): Single<NewsResponse>
}