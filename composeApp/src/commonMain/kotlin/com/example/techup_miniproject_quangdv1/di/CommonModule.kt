package com.example.techup_miniproject_quangdv1.di

import com.example.techup_miniproject_quangdv1.core.utils.ImageByteArrayConverter
import com.example.techup_miniproject_quangdv1.core.utils.ImageByteArrayConverterFactory
import org.koin.dsl.module

val commonModule = module {
    single<ImageByteArrayConverter> {
        get<ImageByteArrayConverterFactory>().create()
    }
}
