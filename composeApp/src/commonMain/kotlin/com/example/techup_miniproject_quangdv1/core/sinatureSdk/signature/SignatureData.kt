package com.example.techup_miniproject_quangdv1.core.sinatureSdk.signature

/**
 * Data class holding the result of signature generation.
 *
 * @property signature The RSA-encrypted, Base64-encoded signature string.
 * @property keyId The API key identifier used in the signature.
 * @property timestamp The server-synchronized timestamp used in the signature.
 */
data class SignatureData(
    val signature: String,
    val keyId: String,
    val timestamp: Long,
)
