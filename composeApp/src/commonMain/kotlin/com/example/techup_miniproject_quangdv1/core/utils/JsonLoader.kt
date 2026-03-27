package com.example.techup_miniproject_quangdv1.core.utils

import com.example.techup_miniproject_quangdv1.data.dto.StyleRequestDto
import io.ktor.utils.io.core.String
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import techup_miniproject_quangdv1.composeapp.generated.resources.Res

@OptIn(ExperimentalResourceApi::class)
suspend fun loadStyles(): StyleRequestDto? =
    try {
        val bytes = Res.readBytes("files/style.json")
        val json = bytes.decodeToString()

        Json {
            ignoreUnknownKeys = true
        }.decodeFromString<StyleRequestDto>(json)
    } catch (e: Exception) {
        null
    }
