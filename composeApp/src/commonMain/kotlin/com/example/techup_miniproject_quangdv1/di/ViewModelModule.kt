package com.example.techup_miniproject_quangdv1.di

import com.example.techup_miniproject_quangdv1.presentation.screens.generatedImage.GeneratedImageViewModel
import com.example.techup_miniproject_quangdv1.presentation.screens.imageGallery.GalleryViewModel
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.ImageInputViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule =
    module {
        viewModelOf(::ImageInputViewModel)
        viewModelOf(::GalleryViewModel)
        viewModelOf(::GeneratedImageViewModel)
    }
