package com.javadsh98.news.data.repository.home.datasource.online

import com.javadsh98.news.data.model.Article

interface IOnlineDataSource {

    suspend fun sendRequest(): List<Article>

}