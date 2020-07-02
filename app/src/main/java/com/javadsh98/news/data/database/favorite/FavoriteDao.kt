package com.javadsh98.news.data.database.favorite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.javadsh98.news.data.model.Article

@Dao
interface FavoriteDao{

    @Insert
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("delete from Article")
    suspend fun deleteAll()

    @Query("select * from Article")
    suspend fun readAll(): LiveData<List<Article>>

}