package com.javadsh98.news.data.repository.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.javadsh98.news.data.database.favorite.FavoriteDao
import com.javadsh98.news.data.model.Article

class FavoriteRepository(var dao: FavoriteDao) : FavoriteDao{
    override suspend fun insert(article: Article) {
        dao.insert(article)
    }

    override suspend fun delete(article: Article) {
        dao.delete(article)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun readAll(): LiveData<List<Article>> {
        return dao.readAll()
    }

}