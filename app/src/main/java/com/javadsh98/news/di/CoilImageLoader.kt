package com.javadsh98.news.di

import coil.ImageLoader
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val imageLoaderModule = module {
    single<ImageLoader> {
        ImageLoader
            .Builder(androidContext())
            .build()
    }
}