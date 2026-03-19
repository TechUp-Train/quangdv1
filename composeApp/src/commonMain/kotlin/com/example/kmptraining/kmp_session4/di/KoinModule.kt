package com.example.kmptraining.kmp_session4.di

import com.example.kmptraining.kmp_session4.data.local.database.AppDatabase
import com.example.kmptraining.kmp_session4.data.remote.service.github.GithubService
import com.example.kmptraining.kmp_session4.data.remote.service.github.GithubServiceImpl
import com.example.kmptraining.kmp_session4.data.repository.ExploreRepositoryImpl
import com.example.kmptraining.kmp_session4.data.repository.UserRepositoryImpl
import com.example.kmptraining.kmp_session4.domain.repository.ExploreRepository
import com.example.kmptraining.kmp_session4.domain.repository.UserRepository
import com.example.kmptraining.kmp_session4.presentation.screens.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<GithubService> {
        GithubServiceImpl(get())
    }

    single<UserRepository> {
        UserRepositoryImpl(get(), get())
    }

    single<ExploreRepository> {
        ExploreRepositoryImpl(get())
    }

    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().userRepoDao() }
}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}