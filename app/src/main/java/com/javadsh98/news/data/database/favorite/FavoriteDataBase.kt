package com.javadsh98.news.data.database.favorite

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javadsh98.news.data.model.Article

@Database(entities = arrayOf(Article::class), version = 2, exportSchema = false)
abstract class FavoriteDataBase : RoomDatabase() {
    abstract fun getDao(): FavoriteDao
}