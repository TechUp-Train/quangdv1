package com.example.techup_miniproject_quangdv1.domain.model

/**
 * Domain model for the pre-signed upload link.
 *
 * @property url  The temporary S3 pre-signed URL to PUT the image bytes into.
 * @property path The storage path that should be passed to the Generate API as a file reference.
 */
data class PresignLinkModel(
    val url: String,
    val path: String
)
