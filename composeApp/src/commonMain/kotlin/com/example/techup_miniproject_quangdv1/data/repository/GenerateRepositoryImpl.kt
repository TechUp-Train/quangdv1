package com.example.techup_miniproject_quangdv1.data.repository

import com.example.techup_miniproject_quangdv1.data.service.ApiService
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageModel
import com.example.techup_miniproject_quangdv1.domain.model.GenerateImageRequest
import com.example.techup_miniproject_quangdv1.domain.repository.GenerateRepository

/**
 * Implementation of [GenerateRepository].
 *
 * Uses [ApiService.TimestampService] for fetching server time
 * and [ApiService.GenerateService] for generating AI-edited images.
 *
 * TODO: User will implement the method bodies later.
 *
 * @property timestampService Service for fetching the server timestamp.
 * @property generateService  Service for calling the Qwen Editing API.
 */
class GenerateRepositoryImpl(
    private val timestampService: ApiService.TimestampService,
    private val generateService: ApiService.GenerateService
) : GenerateRepository {

    /**
     * Fetches the current server timestamp.
     *
     * TODO: Implement — call timestampService.getTimestamp() and extract the timestamp value.
     */
    override suspend fun getTimestamp(): Result<Long> {
        // TODO: implement — user will implement later
        throw NotImplementedError("getTimestamp() is not yet implemented")
    }

    /**
     * Generates an AI-edited image via the Qwen Editing API.
     *
     * TODO: Implement — map [GenerateImageRequest] to DTO, compute signature,
     *       call generateService.generateImage(), then map response to [GenerateImageModel].
     */
    override suspend fun generateImage(request: GenerateImageRequest): Result<GenerateImageModel> {
        // TODO: implement — user will implement later
        throw NotImplementedError("generateImage() is not yet implemented")
    }
}
