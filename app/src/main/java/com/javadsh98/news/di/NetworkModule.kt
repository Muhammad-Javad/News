package com.javadsh98.news.di

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.javadsh98.news.data.model.Article
import com.javadsh98.news.data.model.ArticleResponse
import com.javadsh98.news.data.repository.home.datasource.online.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

val networkModule = module {

    single<OkHttpClient> {
        val logger = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    single<ApiService> {
//        val gson = GsonBuilder().registerTypeAdapter(ArticleResponse::class.java,
//            object : JsonDeserializer<ArticleResponse> {
//                override fun deserialize(
//                    json: JsonElement?,
//                    typeOfT: Type?,
//                    context: JsonDeserializationContext?
//                ): ArticleResponse {
//                    val respose = json?.asJsonObject
//                    val articles = respose?.getAsJsonArray("articles")
//
//                    val articleList = mutableListOf<Article>()
//                    for (i in 0 until articles!!.size()){
//                        var article = articles.get(i).asJsonObject
//                        articleList.add(
//                            Article(id = i
//                            , author = article.get("author").asString
//                            , content = article.get("content").toString() ?: ""
//                            , description = article.get("description").asString
//                                , urlToImage = article.get("urlToImage").toString() ?: ""
//                            , publishedAt = article.get("publishedAt").asString
//                            , title = article.get("title").asString
//                            , url = article.get("url").asString )
//                        )
//                    }
//                    return ArticleResponse(articleList)
//                }
//            }).create()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org")
            .client(get<OkHttpClient>())
            .build()
        retrofit.create(ApiService::class.java)
    }

}