package com.javadsh98.news.di

import com.javadsh98.news.common.internet.IInternetChecker
import com.javadsh98.news.common.internet.InternetCheckerImpl
import com.javadsh98.news.data.database.favorite.FavoriteDao
import com.javadsh98.news.data.database.home.HomeDao
import com.javadsh98.news.data.repository.favorite.FavoriteRepository
import com.javadsh98.news.data.repository.home.HomeRepository
import com.javadsh98.news.data.repository.home.IHomeRepository
import com.javadsh98.news.data.repository.home.datasource.offline.IOfflineDataSource
import com.javadsh98.news.data.repository.home.datasource.offline.OfflineDataSourceImpl
import com.javadsh98.news.data.repository.home.datasource.online.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    //favorite
    single {
        FavoriteRepository(get<FavoriteDao>())
    }

    //home
    //online
    single<INetworkHelper> {
        NetworkHelperImpl(get<ApiService>())
    }

    single<IOnlineDataSource> {
        OnlineDataSourceImpl(get<INetworkHelper>())
    }

    //offline
    single<IOfflineDataSource> {
        OfflineDataSourceImpl(get<HomeDao>())
    }

    //internet checker
    single<IInternetChecker> {
        InternetCheckerImpl(androidContext())
    }

    single<IHomeRepository> {
        HomeRepository(get<IOnlineDataSource>()
            , get<IOfflineDataSource>()
            , get<IInternetChecker>()
        )
    }

}