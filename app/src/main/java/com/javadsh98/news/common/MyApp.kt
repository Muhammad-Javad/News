package com.javadsh98.news.common

import android.app.Application
import com.javadsh98.news.BuildConfig
import com.javadsh98.news.di.databaseModule
import com.javadsh98.news.di.networkModule
import com.javadsh98.news.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@MyApp)
            modules(databaseModule, repositoryModule, networkModule)
        }

    }

}