package com.javadsh98.news.di

import androidx.room.Room
import com.javadsh98.news.data.database.favorite.FavoriteDao
import com.javadsh98.news.data.database.favorite.FavoriteDataBase
import com.javadsh98.news.data.database.home.HomeDao
import com.javadsh98.news.data.database.home.HomeDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room
        .databaseBuilder(androidContext(), FavoriteDataBase::class.java, "favorite")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        Room
            .databaseBuilder(androidContext(), HomeDataBase::class.java, "home")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single<FavoriteDao> {
        get<FavoriteDataBase>().getDao()
    }

    single<HomeDao> {
        get<HomeDataBase>().getDao()
    }

}