package com.example.techup_miniproject_quangdv1.di

import com.example.techup_miniproject_quangdv1.data.repository.GenerateRepositoryImpl
import com.example.techup_miniproject_quangdv1.data.repository.PickImageRepositoryImpl
import com.example.techup_miniproject_quangdv1.data.repository.PresignRepositoryImpl
import com.example.techup_miniproject_quangdv1.data.repository.StyleRepositoryImpl
import com.example.techup_miniproject_quangdv1.domain.repository.GenerateRepository
import com.example.techup_miniproject_quangdv1.domain.repository.PickImageRepository
import com.example.techup_miniproject_quangdv1.domain.repository.PresignRepository
import com.example.techup_miniproject_quangdv1.domain.repository.StyleRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<PresignRepository> {
        PresignRepositoryImpl(
            dataSource = get(),
            timestampProvider = get(),
        )
    }

    single<GenerateRepository> {
        GenerateRepositoryImpl(
            dataSource = get(),
        )
    }

    single<StyleRepository> {
        StyleRepositoryImpl()
    }

    single<PickImageRepository> {
        PickImageRepositoryImpl(
            mediaPermissionManager = get(),
            galleryImageSource = get(),
        )
    }
}
