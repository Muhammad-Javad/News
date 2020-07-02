package com.javadsh98.news.data.repository.home.datasource.online

import com.javadsh98.news.data.model.Article
import com.javadsh98.news.data.model.ArticleResponse
import kotlinx.coroutines.flow.Flow

interface INetworkHelper {

    suspend fun getNews(page: Int): Flow<ArticleResponse>

}