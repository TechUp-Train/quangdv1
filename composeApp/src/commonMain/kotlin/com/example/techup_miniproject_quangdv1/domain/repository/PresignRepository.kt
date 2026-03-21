package com.example.techup_miniproject_quangdv1.domain.repository

import com.example.techup_miniproject_quangdv1.domain.model.PresignLinkModel

/**
 * Repository interface for pre-sign and upload operations.
 *
 * Handles:
 * 1. Obtaining a temporary pre-signed S3 URL for image upload.
 * 2. Uploading raw image bytes to the pre-signed URL.
 *
 * TODO: Implement in PresignRepositoryImpl — user will implement later.
 */
interface PresignRepository {

    /**
     * Fetches a temporary pre-signed URL from the server.
     * This URL is used to upload the user's input image to cloud storage.
     *
     * @return [Result] wrapping [PresignLinkModel] with the upload URL and storage path.
     */
    suspend fun getPresignLink(): Result<PresignLinkModel>

    /**
     * Uploads raw image bytes to the given pre-signed S3 URL.
     *
     * @param presignUrl The temporary S3 URL obtained from [getPresignLink].
     * @param imageBytes The raw bytes of the image to upload.
     * @return [Result] wrapping [Boolean] — true if upload succeeded.
     */
    suspend fun uploadImage(presignUrl: String, imageBytes: ByteArray): Result<Boolean>
}