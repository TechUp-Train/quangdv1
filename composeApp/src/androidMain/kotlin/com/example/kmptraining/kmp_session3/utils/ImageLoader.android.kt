package com.example.kmptraining.kmp_session3.utils


import android.content.Context
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import okio.FileSystem
import okio.Path.Companion.toPath

@OptIn(ExperimentalCoilApi::class)
actual fun createImageLoader(context: PlatformContext): ImageLoader {
    val androidContext = context as Context

    return ImageLoader.Builder(context)
        .components {
            add(KtorNetworkFetcherFactory(HttpClient()))
        }
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context, 0.25)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(
                    androidContext.cacheDir
                        .resolve("image_cache")
                        .absolutePath
                        .toPath()
                )
                .fileSystem(FileSystem.SYSTEM)
                .maxSizeBytes(100L * 1024 * 1024)
                .build()
        }
        .crossfade(true)
        .build()
}
