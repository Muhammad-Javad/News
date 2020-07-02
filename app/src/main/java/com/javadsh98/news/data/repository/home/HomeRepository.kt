package com.javadsh98.news.data.repository.home

import com.javadsh98.news.common.internet.IInternetChecker
import com.javadsh98.news.data.model.Article
import com.javadsh98.news.data.repository.home.datasource.offline.IOfflineDataSource
import com.javadsh98.news.data.repository.home.datasource.online.IOnlineDataSource

class HomeRepository(val online: IOnlineDataSource
                     , val offline: IOfflineDataSource
                     , val internetchecker:  IInternetChecker): IHomeRepository{

    override suspend fun getNews(): List<Article> {

        if (internetchecker.isConnected()){
            offline.deleteAll()

            val articles = online.sendRequest()
            offline.insertAll(articles)

            return articles
        }else{
            return offline.readAll()
        }
    }

}