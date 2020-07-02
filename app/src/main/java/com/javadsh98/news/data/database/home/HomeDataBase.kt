package com.javadsh98.news.data.database.home

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javadsh98.news.data.model.Article

@Database(entities = arrayOf(Article::class), version = 1, exportSchema = false )
abstract class HomeDataBase : RoomDatabase(){
    abstract fun getDao(): HomeDao
}