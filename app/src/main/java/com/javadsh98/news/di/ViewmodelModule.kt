package com.javadsh98.news.di

import com.javadsh98.news.data.repository.home.IHomeRepository
import com.javadsh98.news.ui.main.fragment.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {

    viewModel {
        HomeViewModel(get<IHomeRepository>())
    }

}