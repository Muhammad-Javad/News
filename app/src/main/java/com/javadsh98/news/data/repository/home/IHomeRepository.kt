package com.javadsh98.news.data.repository.home

import com.javadsh98.news.data.model.Article

interface IHomeRepository {

    suspend fun getNews() = emptyList<Article>()

    fun checkNullData(articles: List<Article>) =
        articles.map {
            Article(
                id = 0
                , title = it.title ?: ""
                , description = it.description ?: ""
                , content = it.content ?: ""
                , url = it.url ?: ""
                , author = it.author ?: ""
                , publishedAt = it.publishedAt ?: ""
                , urlToImage = it.urlToImage ?: ""
            )
        }

}
