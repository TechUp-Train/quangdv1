package com.example.techup_miniproject_quangdv1.data.repository

import com.example.techup_miniproject_quangdv1.data.service.ApiService
import com.example.techup_miniproject_quangdv1.domain.model.PresignLinkModel
import com.example.techup_miniproject_quangdv1.domain.repository.PresignRepository

/**
 * Implementation of [PresignRepository].
 *
 * Uses [ApiService.PresignService] for obtaining pre-signed URLs
 * and [ApiService.UploadService] for uploading images.
 *
 * TODO: User will implement the method bodies later.
 *
 * @property presignService Service for fetching pre-signed upload links.
 * @property uploadService  Service for uploading image bytes to S3.
 */
class PresignRepositoryImpl(
    private val presignService: ApiService.PresignService,
    private val uploadService: ApiService.UploadService
) : PresignRepository {

    /**
     * Fetches a temporary pre-signed URL from the server.
     *
     * TODO: Implement — call presignService.getPresignLink() with proper signature/timestamp,
     *       then map the DTO to [PresignLinkModel].
     */
    override suspend fun getPresignLink(): Result<PresignLinkModel> {
        // TODO: implement — user will implement later
        throw NotImplementedError("getPresignLink() is not yet implemented")
    }

    /**
     * Uploads raw image bytes to the given pre-signed S3 URL.
     *
     * TODO: Implement — call uploadService.uploadImage() and return success/failure.
     */
    override suspend fun uploadImage(presignUrl: String, imageBytes: ByteArray): Result<Boolean> {
        // TODO: implement — user will implement later
        throw NotImplementedError("uploadImage() is not yet implemented")
    }
}
