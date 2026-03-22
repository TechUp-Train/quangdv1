package com.example.techup_miniproject_quangdv1.di

import com.example.techup_miniproject_quangdv1.data.repository.GenerateRepositoryImpl
import com.example.techup_miniproject_quangdv1.data.repository.PresignRepositoryImpl
import com.example.techup_miniproject_quangdv1.domain.repository.GenerateRepository
import com.example.techup_miniproject_quangdv1.domain.repository.PresignRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<PresignRepository> {
        PresignRepositoryImpl(
            dataSource = get()
        )
    }

    single<GenerateRepository> {
        GenerateRepositoryImpl(
            dataSource = get(),
            timestampProvider = get()
        )
    }
}
