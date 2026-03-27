package com.example.techup_miniproject_quangdv1.core.utils

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.readRawBytes

interface ImageDownloader {
    suspend fun download(url: String): ByteArray
}

class KtorImageDownloader(
    private val client: HttpClient,
) : ImageDownloader {
    override suspend fun download(url: String): ByteArray = client.get(url).readRawBytes()
}
