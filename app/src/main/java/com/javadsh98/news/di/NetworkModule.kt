package com.javadsh98.news.di

import com.javadsh98.news.data.repository.home.datasource.online.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<OkHttpClient> {
        val logger = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    single<ApiService> {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org")
            .client(get<OkHttpClient>())
            .build()
        retrofit.create(ApiService::class.java)
    }

}