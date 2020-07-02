package com.javadsh98.news.data.database.home

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.javadsh98.news.data.model.Article


@Dao
interface HomeDao {

    @Insert
    suspend fun insertAll(articles: List<Article>)

    @Query("delete from Article")
    suspend fun deleteAll()

    @Query("select * from Article")
    suspend fun readAll(): List<Article>

}