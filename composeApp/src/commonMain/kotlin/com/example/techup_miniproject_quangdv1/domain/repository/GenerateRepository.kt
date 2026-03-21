package com.example.techup_miniproject_quangdv1.domain.repository

import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageModel
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageRequest

/**
 * Repository interface for timestamp retrieval and AI image generation.
 *
 * Handles:
 * 1. Fetching the server timestamp (for API signature synchronization).
 * 2. Calling the Qwen Editing API to generate AI-edited images.
 *
 * TODO: Implement in GenerateRepositoryImpl — user will implement later.
 */
interface GenerateRepository {

    /**
     * Fetches the current server timestamp.
     * Used to synchronize with the server for API signature generation.
     * The service accepts a max 4-minute difference between client and server time.
     *
     * @return [Result] wrapping the server timestamp as [Long] (Unix millis).
     */
    suspend fun getTimestamp(): Result<Long>

    /**
     * Generates an AI-edited image using the Qwen Editing engine.
     *
     * @param request The [GenerateImageRequest] containing files, mode, and prompt.
     * @return [Result] wrapping [GenerateImageModel] with the output image URL and path.
     */
    suspend fun generateImage(request: GenerateImageRequest): Result<GenerateImageModel>
}