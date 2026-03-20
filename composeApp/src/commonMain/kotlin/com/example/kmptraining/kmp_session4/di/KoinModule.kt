package com.example.kmptraining.kmp_session4.di

import com.example.kmptraining.kmp_session4.data.local.dataSource.user.UserLocalDataSourceImpl
import com.example.kmptraining.kmp_session4.data.remote.dataSource.user.UserRemoteDataSourceImpl
import com.example.kmptraining.kmp_session4.data.local.dataSource.userRepos.UserReposLocalDataSourceImpl
import com.example.kmptraining.kmp_session4.data.remote.dataSource.userRepos.UserReposRemoteDataSourceImpl
import com.example.kmptraining.kmp_session4.data.local.database.AppDatabase
import com.example.kmptraining.kmp_session4.data.remote.service.github.GithubService
import com.example.kmptraining.kmp_session4.data.remote.service.github.GithubServiceImpl
import com.example.kmptraining.kmp_session4.data.repository.PublicReposRepositoryImpl
import com.example.kmptraining.kmp_session4.data.repository.UserReposRepositoryImpl
import com.example.kmptraining.kmp_session4.data.repository.UserRepositoryImpl
import com.example.kmptraining.kmp_session4.data.local.dataSource.publicRepos.PublicReposLocalDataSourceImpl
import com.example.kmptraining.kmp_session4.data.remote.dataSource.publicRepos.PublicReposRemoteDataSourceImpl
import com.example.kmptraining.kmp_session4.data.dataSource.publicRepos.PublicReposLocalDataSource
import com.example.kmptraining.kmp_session4.data.dataSource.publicRepos.PublicReposRemoteDataSource
import com.example.kmptraining.kmp_session4.data.dataSource.user.UserLocalDataSource
import com.example.kmptraining.kmp_session4.data.dataSource.user.UserRemoteDataSource
import com.example.kmptraining.kmp_session4.data.dataSource.userRepos.UserReposLocalDataSource
import com.example.kmptraining.kmp_session4.data.dataSource.userRepos.UserReposRemoteDataSource
import com.example.kmptraining.kmp_session4.domain.repository.PublicReposRepository
import com.example.kmptraining.kmp_session4.domain.repository.UserReposRepository
import com.example.kmptraining.kmp_session4.domain.repository.UserRepository
import com.example.kmptraining.kmp_session4.presentation.screens.explore.ExploreViewModel
import com.example.kmptraining.kmp_session4.presentation.screens.home.HomeViewModel
import com.example.kmptraining.kmp_session4.presentation.screens.profile.ProfileViewModel
import com.example.kmptraining.kmp_session4.presentation.screens.repoDetail.RepoViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<GithubService> {
        GithubServiceImpl(get())
    }

    //Use case


    // Repository
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<UserReposRepository> { UserReposRepositoryImpl(get(), get()) }
    single<PublicReposRepository> { PublicReposRepositoryImpl(get(), get()) }

    //DataSource
    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }

    single<UserReposLocalDataSource> { UserReposLocalDataSourceImpl(get()) }
    single<UserReposRemoteDataSource> { UserReposRemoteDataSourceImpl(get()) }

    single<PublicReposLocalDataSource> { PublicReposLocalDataSourceImpl(get()) }
    single<PublicReposRemoteDataSource> { PublicReposRemoteDataSourceImpl(get()) }

    // DAOs
    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().repoDao() }
}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::ExploreViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::RepoViewModel)
}