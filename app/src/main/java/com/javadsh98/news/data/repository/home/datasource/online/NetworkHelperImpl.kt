package com.javadsh98.news.data.repository.home.datasource.online


import com.javadsh98.news.data.model.ArticleResponse
import kotlinx.coroutines.flow.flow

class NetworkHelperImpl(val api: ApiService): INetworkHelper{

    override suspend fun getNews(page: Int) = flow<ArticleResponse> {
        api.getBitcoinNews(page = page)
    }

}