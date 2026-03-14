package com.example.kmptraining.kmp_session2.di

import com.example.kmptraining.kmp_session2.data.network.RemoteDataSourceImpl
import com.example.kmptraining.kmp_session2.data.network.RemoteNewsDataSource
import com.example.kmptraining.kmp_session2.domain.repository.news.NewsRepository
import com.example.kmptraining.kmp_session2.domain.repository.news.NewsRepositoryImpl
import com.example.kmptraining.kmp_session2.presentation.home.HomeViewModel
import com.example.kmptraining.kmp_session2.presentation.newsDetail.NewsDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<RemoteNewsDataSource> { RemoteDataSourceImpl() }
    single<NewsRepository> { NewsRepositoryImpl(get()) }
    factory { HomeViewModel(get()) }
    factory { NewsDetailViewModel(get()) }
}