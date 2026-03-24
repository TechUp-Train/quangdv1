package com.example.techup_miniproject_quangdv1

import android.app.Application
import com.example.techup_miniproject_quangdv1.di.androidModule
import com.example.techup_miniproject_quangdv1.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            appDeclaration = {
                androidContext(this@MainApplication)
            },
             androidModule,
        )
    }
}
