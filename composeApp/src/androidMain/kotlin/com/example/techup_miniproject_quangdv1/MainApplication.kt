package com.example.techup_miniproject_quangdv1

import android.app.Application
import com.example.techup_miniproject_quangdv1.di.initKoin

/**
 * Custom Application class for Android.
 * Initializes Koin at the application level so DI is available before any Activity starts.
 *
 * Don't forget to register this in AndroidManifest.xml:
 * <application android:name=".App" ... />
 *
 * Note: This is the Android Application class, separate from the Compose App() function.
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
