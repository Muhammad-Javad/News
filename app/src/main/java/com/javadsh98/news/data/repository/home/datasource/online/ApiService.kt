package com.javadsh98.news.data.repository.home.datasource.online

import com.javadsh98.news.data.model.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/everything")
    suspend fun getBitcoinNews(
        @Query("qInTitle") title: String = "bitcoin"
        , @Query("apiKey") key: String = "e96921eddee141d38b6919340d7071f3"
        , @Query("page") page: Int
        , @Query("pageSize") pageSize: Int = 5
    ): ArticleResponse

}