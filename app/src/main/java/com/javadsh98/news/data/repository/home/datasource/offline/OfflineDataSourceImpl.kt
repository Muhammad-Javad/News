package com.javadsh98.news.data.repository.home.datasource.offline

import androidx.lifecycle.LiveData
import com.javadsh98.news.data.database.home.HomeDao
import com.javadsh98.news.data.model.Article

class OfflineDataSourceImpl(val dao: HomeDao) : IOfflineDataSource{

    override suspend fun insertAll(articles: List<Article>) {
        dao.insertAll(articles)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun readAll(): List<Article> {
        return dao.readAll()
    }

}