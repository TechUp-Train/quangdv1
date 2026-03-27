package com.example.techup_miniproject_quangdv1.di

import com.example.techup_miniproject_quangdv1.core.utils.AndroidFileSaver
import com.example.techup_miniproject_quangdv1.core.utils.FileSaver
import com.example.techup_miniproject_quangdv1.core.utils.ImageByteArrayConverterFactory
import org.koin.dsl.module

val androidModule =
    module {
        single {
            ImageByteArrayConverterFactory(get())
        }

        single<FileSaver> { AndroidFileSaver(get()) }
    }
