package com.example.techup_miniproject_quangdv1.core.di

import com.example.techup_miniproject_quangdv1.core.utils.ConnectivityMonitorFactory
import com.example.techup_miniproject_quangdv1.core.utils.FileSaver
import com.example.techup_miniproject_quangdv1.core.utils.IOSFileSaver
import com.example.techup_miniproject_quangdv1.core.utils.ImageByteArrayConverterFactory
import org.koin.dsl.module

val iosModule = module {
    single {
        ImageByteArrayConverterFactory()
    }

    single<FileSaver> { IOSFileSaver() }

    single { ConnectivityMonitorFactory() }
}