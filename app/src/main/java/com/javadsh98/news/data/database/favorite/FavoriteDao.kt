package com.javadsh98.news.data.database.favorite

import androidx.lifecycle.LiveData
import androidx.room.*
import com.javadsh98.news.data.model.Article

@Dao
interface FavoriteDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("delete from Article")
    suspend fun deleteAll()

    @Query("select * from Article")
    fun readAll(): LiveData<List<Article>>

    @Query("select * from Article where title like '%' || :query || '%'")
    fun readByQuery(query: String): LiveData<List<Article>>

}