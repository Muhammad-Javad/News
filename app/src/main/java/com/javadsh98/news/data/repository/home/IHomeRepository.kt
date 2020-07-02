package com.javadsh98.news.data.repository.home

import com.javadsh98.news.data.model.Article

interface IHomeRepository {

    suspend fun getNews() = emptyList<Article>()

}
