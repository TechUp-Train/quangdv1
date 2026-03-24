package com.example.techup_miniproject_quangdv1.di

import com.example.techup_miniproject_quangdv1.domain.useCase.convertImage.ConvertImageUseCase
import com.example.techup_miniproject_quangdv1.domain.useCase.convertImage.ConvertImageUseCaseImpl
import com.example.techup_miniproject_quangdv1.domain.useCase.downloadImage.DownloadUseCase
import com.example.techup_miniproject_quangdv1.domain.useCase.downloadImage.DownloadUseCaseImpl
import com.example.techup_miniproject_quangdv1.domain.useCase.generateImage.GenerateImageUseCase
import com.example.techup_miniproject_quangdv1.domain.useCase.generateImage.GenerateImageUseCaseImpl
import com.example.techup_miniproject_quangdv1.domain.useCase.getStyles.GetStyleUseCase
import com.example.techup_miniproject_quangdv1.domain.useCase.getStyles.GetStyleUseCaseImpl
import com.example.techup_miniproject_quangdv1.domain.useCase.observeConnectivity.ObserveConnectivityUseCase
import com.example.techup_miniproject_quangdv1.domain.useCase.observeConnectivity.ObserveConnectivityUseCaseImpl
import com.example.techup_miniproject_quangdv1.domain.useCase.pickImages.PickImagesUseCase
import com.example.techup_miniproject_quangdv1.domain.useCase.pickImages.PickImagesUseCaseImpl
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

    factory<GetStyleUseCase> {
        GetStyleUseCaseImpl(
            styleRepository = get(),
        )
    }

    factory<PickImagesUseCase> {
        PickImagesUseCaseImpl(
            pickImageRepository = get(),
        )
    }

    factory<ConvertImageUseCase> {
        ConvertImageUseCaseImpl(
            imageByteArrayConverter = get(),
        )
    }

    factory<DownloadUseCase> {
        DownloadUseCaseImpl(
            downloader = get(),
            fileSaver = get(),
        )
    }

    factory<ObserveConnectivityUseCase> {
        ObserveConnectivityUseCaseImpl(
            connectivityMonitor = get(),
        )
    }
}
