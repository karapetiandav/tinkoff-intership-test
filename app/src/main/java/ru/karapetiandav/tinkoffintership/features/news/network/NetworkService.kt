package ru.karapetiandav.tinkoffintership.features.news.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor() {

    companion object {

        @Volatile
        private var INSTANCE: NetworkService? = null

        fun getInstance(): NetworkService = INSTANCE ?: synchronized(this) {
            INSTANCE ?: NetworkService().also { INSTANCE = it }
        }
    }

    private val BASE_URL = "https://api.tinkoff.ru/v1/"
    private var retrofit: Retrofit

    init {

        val httpClient = buildHttpClient()

        retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun buildHttpClient(): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return httpClient
    }

    fun getTinkoffApi(): TinkoffNewsApi {
        return retrofit.create(TinkoffNewsApi::class.java)
    }
}