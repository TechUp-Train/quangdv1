package com.example.techup_miniproject_quangdv1.di

import com.example.techup_miniproject_quangdv1.domain.useCase.generateImage.GenerateImageUseCase
import com.example.techup_miniproject_quangdv1.domain.useCase.generateImage.GenerateImageUseCaseImpl
import com.example.techup_miniproject_quangdv1.domain.useCase.processImage.ProcessImageUseCase
import com.example.techup_miniproject_quangdv1.domain.useCase.processImage.ProcessImageUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<ProcessImageUseCase> {
        ProcessImageUseCaseImpl(
            presignRepository = get(),
        )
    }

    factory<GenerateImageUseCase> {
        GenerateImageUseCaseImpl(
            generateRepository = get(),
            presignRepository = get(),
        )
    }
}
