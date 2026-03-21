package com.example.techup_miniproject_quangdv1.data.service

import com.example.techup_miniproject_quangdv1.data.model.ApiResponseDto
import com.example.techup_miniproject_quangdv1.data.model.GenerateImageRequestDto
import com.example.techup_miniproject_quangdv1.data.model.GenerateImageResponseDto
import com.example.techup_miniproject_quangdv1.data.model.PresignLinkDto
import com.example.techup_miniproject_quangdv1.data.model.TimestampDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

/**
 * Sealed interface representing all available API services.
 * Each concrete service encapsulates a single API endpoint's call logic.
 *
 * Factory pattern is applied via [ApiServiceFactory] to instantiate these services.
 */
sealed interface ApiService {

    // ── Timestamp Service ──────────────────────────────────────────────────────

    /**
     * Fetches the server timestamp for API signature synchronization.
     * Endpoint: GET https://video-gen-core.aperogroup.ai/timestamp
     */
    class TimestampService(private val client: HttpClient) : ApiService {

        suspend fun getTimestamp(): TimestampDto {
            return client.get("${ApiConstants.TIMESTAMP_BASE_URL}${ApiConstants.TIMESTAMP_ENDPOINT}") {
                header(ApiConstants.HEADER_APP_NAME, ApiConstants.APP_NAME)
                header(ApiConstants.HEADER_BUNDLE_ID, ApiConstants.BUNDLE_ID)
                header(ApiConstants.HEADER_DEVICE_ID, ApiConstants.DEVICE_ID)
                header(ApiConstants.HEADER_COUNTRY_CODE, ApiConstants.COUNTRY_CODE)
                header(ApiConstants.HEADER_APP_VERSION, ApiConstants.APP_VERSION)
            }.body()
        }
    }

    // ── Presign Link Service ───────────────────────────────────────────────────

    /**
     * Obtains a temporary pre-signed URL for image upload.
     * Endpoint: GET /api/v5.1/qwen-editing/presigned-link
     */
    class PresignService(private val client: HttpClient) : ApiService {

        suspend fun getPresignLink(
            signature: String,
            timestamp: String
        ): ApiResponseDto<PresignLinkDto> {
            return client.get("${ApiConstants.BASE_URL}${ApiConstants.PRESIGN_LINK_ENDPOINT}") {
                header(ApiConstants.HEADER_BUNDLE_ID, ApiConstants.BUNDLE_ID)
                header(ApiConstants.HEADER_SIGNATURE, signature)
                header(ApiConstants.HEADER_TIMESTAMP, timestamp)
                header(ApiConstants.HEADER_TOKEN, ApiConstants.TOKEN)
                header(ApiConstants.HEADER_APP_NAME, ApiConstants.APP_NAME)
                header(ApiConstants.HEADER_COUNTRY_CODE, ApiConstants.COUNTRY_CODE)
                header(ApiConstants.HEADER_APP_VERSION, ApiConstants.APP_VERSION)
                header(ApiConstants.HEADER_DEVICE_ID, ApiConstants.DEVICE_ID)
            }.body()
        }
    }

    // ── Upload Service ─────────────────────────────────────────────────────────

    /**
     * Uploads raw image bytes to the pre-signed S3 URL.
     * Endpoint: PUT <presigned_url>
     */
    class UploadService(private val client: HttpClient) : ApiService {

        suspend fun uploadImage(
            presignedUrl: String,
            imageBytes: ByteArray
        ) {
            client.put(presignedUrl) {
                contentType(ContentType.Image.JPEG)
                setBody(imageBytes)
            }
        }
    }

    // ── Generate Image Service ─────────────────────────────────────────────────

    /**
     * Generates an AI-edited image using the Qwen Editing engine.
     * Endpoint: POST /api/v5.1/qwen-editing
     */
    class GenerateService(private val client: HttpClient) : ApiService {

        suspend fun generateImage(
            request: GenerateImageRequestDto,
            signature: String,
            timestamp: String
        ): ApiResponseDto<GenerateImageResponseDto> {
            return client.post("${ApiConstants.BASE_URL}${ApiConstants.GENERATE_ENDPOINT}") {
                header(ApiConstants.HEADER_BUNDLE_ID, ApiConstants.BUNDLE_ID)
                header(ApiConstants.HEADER_SIGNATURE, signature)
                header(ApiConstants.HEADER_TIMESTAMP, timestamp)
                header(ApiConstants.HEADER_TOKEN, ApiConstants.TOKEN)
                header(ApiConstants.HEADER_APP_NAME, ApiConstants.APP_NAME)
                header(ApiConstants.HEADER_COUNTRY_CODE, ApiConstants.COUNTRY_CODE)
                header(ApiConstants.HEADER_APP_VERSION, ApiConstants.APP_VERSION)
                header(ApiConstants.HEADER_DEVICE_ID, ApiConstants.DEVICE_ID)
                setBody(request)
            }.body()
        }
    }
}
