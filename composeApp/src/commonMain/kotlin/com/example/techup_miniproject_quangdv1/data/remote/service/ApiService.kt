package com.example.techup_miniproject_quangdv1.data.remote.service

import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageRequestDto
import com.example.techup_miniproject_quangdv1.data.dto.GenerateImageResponseDto
import com.example.techup_miniproject_quangdv1.data.dto.PresignLinkDto
import com.example.techup_miniproject_quangdv1.data.dto.PresignResponseDto
import com.example.techup_miniproject_quangdv1.data.dto.TimestampDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.expectSuccess
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

sealed interface ApiService {
    class TimestampService(
        private val client: HttpClient,
    ) : ApiService {
        suspend fun getTimestamp(): TimestampDto =
            client
                .get("${ApiConstants.TIMESTAMP_BASE_URL}${ApiConstants.TIMESTAMP_ENDPOINT}") {
                    header(ApiConstants.HEADER_APP_NAME, ApiConstants.APP_NAME)
                    header(ApiConstants.HEADER_BUNDLE_ID, ApiConstants.BUNDLE_ID)
                    header(ApiConstants.HEADER_DEVICE_ID, ApiConstants.DEVICE_ID)
                    header(ApiConstants.HEADER_COUNTRY_CODE, ApiConstants.COUNTRY_CODE)
                    header(ApiConstants.HEADER_APP_VERSION, ApiConstants.APP_VERSION)
                }.body()
    }

    class PresignService(
        private val client: HttpClient,
    ) : ApiService {
        suspend fun getPresignLink(): PresignResponseDto<PresignLinkDto> =
            client.get("${ApiConstants.BASE_URL}${ApiConstants.PRESIGN_LINK_ENDPOINT}").body()
    }

    class UploadService(
        private val client: HttpClient,
    ) : ApiService {
        suspend fun uploadImage(
            presignedUrl: String,
            imageBytes: ByteArray,
        ) {
            client.put(presignedUrl) {
                contentType(ContentType.Image.JPEG)
                setBody(imageBytes)
                expectSuccess = true
            }
        }
    }

    class GenerateService(
        private val client: HttpClient,
    ) : ApiService {
        suspend fun generateImage(request: GenerateImageRequestDto): PresignResponseDto<GenerateImageResponseDto> =
            client
                .post("${ApiConstants.BASE_URL}${ApiConstants.GENERATE_ENDPOINT}") {
                    setBody(request)
                }.body()
    }
}
