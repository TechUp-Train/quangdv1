package com.example.kmptraining.kmp_session2.di

import com.example.kmptraining.kmp_session2.data.network.RemoteNewsDataSource
import com.example.kmptraining.kmp_session2.domain.repository.news.NewsRepository
import com.example.kmptraining.kmp_session2.domain.repository.news.NewsRepositoryImpl
import com.example.kmptraining.kmp_session2.presentation.home.HomeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::NewsRepositoryImpl).bind<NewsRepository>()
    single<NewsRepository> { NewsRepositoryImpl(get()) }
    viewModelOf(::HomeViewModel)
}
