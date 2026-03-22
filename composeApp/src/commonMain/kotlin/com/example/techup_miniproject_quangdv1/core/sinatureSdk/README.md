# Signature KMP

Standalone Kotlin Multiplatform module for generating RSA-based API request signatures. Extracted from the AiService KMP SDK for reuse across projects.

## Overview

This module provides **signature-based authentication** for API requests. Every outgoing HTTP request is signed with an RSA-encrypted payload containing a timestamp, API key, and random nonce — ensuring request authenticity and preventing replay attacks.

## Folder Structure

```
signature-kmp/
├── commonMain/                        # Shared (expect) declarations
│   ├── SignatureData.kt               # Data class for signature result
│   ├── SignatureParser.kt             # Interface + expect fun for platform impl
│   ├── SignatureInterceptor.kt        # Ktor HTTP client interceptor
├── androidMain/                       # Android (actual) implementations
│   ├── SignatureParser.android.kt     # RSA via javax.crypto.Cipher
├── iosMain/                           # iOS (actual) implementations
│   ├── SignatureParser.ios.kt         # RSA via Apple Security framework
└── README.md
```

## How Signature Generation Works

### Algorithm

1. **Construct plaintext**: `"<timestamp>@@@<apiKey>@@@<nonce>"`
   - `timestamp` — epoch seconds (server-synchronized)
   - `apiKey` — your API key identifier
   - `nonce` — random integer in `[0, 1_000_000)` to prevent replay

2. **Encrypt** the plaintext using RSA with PKCS1 padding and your RSA public key (PEM/X.509 format)

3. **Encode** the encrypted bytes as Base64 — this is the `x-api-signature` header value

### Platform Implementations

| Platform | RSA Engine | Key Format | Base64 |
|----------|-----------|------------|--------|
| **Android** | `javax.crypto.Cipher("RSA/None/PKCS1Padding")` | X.509 via `KeyFactory` | `android.util.Base64` |
| **iOS** | `SecKeyCreateEncryptedData` + `kSecKeyAlgorithmRSAEncryptionPKCS1` | DER via `SecKeyCreateWithData` | Ktor `encodeBase64()` |

## Files Description

### `SignatureData.kt` (commonMain)
Simple data class holding the signature result:
- `signature: String` — the Base64-encoded RSA ciphertext
- `keyId: String` — the API key used
- `timestamp: Long` — the timestamp used

### `SignatureParser.kt` (commonMain)
The core interface with `expect/actual` pattern:
- `parse(keyId, publicKeyPem, timestamp) -> Result<SignatureData>`
- Companion `parseData()` for convenient static access
- `expect fun signatureParserImpl()` delegates to platform

### `SignatureInterceptor.kt` (commonMain)
Ktor `HttpClient` interceptor that automatically attaches these headers to every request:

| Header | Value |
|--------|-------|
| `x-api-signature` | RSA-encrypted signature |
| `x-api-timestamp` | Epoch timestamp |
| `x-api-bundleId` | App bundle ID |
| `x-api-token` | Token placeholder |
| `App-name` | Application name |
| `country-code` | Country code (optional) |
| `app-version` | App version (optional) |
| `x-api-deviceid` | Device ID (optional) |

## Usage

### 1. Direct Signature Generation

```kotlin
val result = SignatureParser.parseData(
    keyId = "your-api-key",
    publicKeyPem = """
        -----BEGIN PUBLIC KEY-----
        MIIBIjANBgkqhk...your-key-here...
        -----END PUBLIC KEY-----
    """.trimIndent(),
    timestamp = currentEpochSeconds()
)

result.onSuccess { data ->
    println("Signature: ${data.signature}")
    println("Timestamp: ${data.timestamp}")
}
result.onFailure { error ->
    println("Failed: ${error.message}")
}
```

### 2. Ktor HTTP Client Interceptor

```kotlin
val client = HttpClient {
    installSignatureInterceptor(
        apiKey = "your-api-key",
        publicKey = "-----BEGIN PUBLIC KEY-----\n...\n-----END PUBLIC KEY-----",
        bundleId = "com.example.app",
        appName = "MyApp",
        appVersion = "1.0.0",
        countryCode = "US",
        deviceId = "device-uuid"
    )
}

// All requests now automatically include signature headers
val response = client.get("https://api.example.com/data")
```

### 3. Custom Timestamp Provider

```kotlin
val client = HttpClient {
    installSignatureInterceptor(
        apiKey = "...",
        publicKey = "...",
        bundleId = "...",
        appName = "...",
        timestampProvider = { serverSyncedTimestamp() } // your own time source
    )
}
```

## Dependencies

| Dependency | Purpose |
|-----------|---------|
| **Ktor Client** (`io.ktor:ktor-client-core`) | HTTP client interceptor, Base64 utils |
| **kotlinx-cinterop** (iOS only) | Native C interop for Security/CoreFoundation |
| **FileKit** (`io.github.vinceglb:filekit`) | `ByteArray.toNSData()` on iOS |

## Integration into a KMP Project

1. Copy the `signature-kmp/` folder into your project
2. Place files under the matching source sets:
   - `commonMain/kotlin/com/apero/signature/` — shared files
   - `androidMain/kotlin/com/apero/signature/` — Android files
   - `iosMain/kotlin/com/apero/signature/` — iOS files
3. Update the `package` declaration if needed
4. Add Ktor and FileKit dependencies to your `build.gradle.kts`

## Security Notes

- The RSA public key must be in **X.509/SPKI PEM format**
- A random nonce is included in every signature to prevent replay attacks
- Timestamps should be server-synchronized to avoid clock skew rejections (the SDK uses `ERR05` error code to detect expired timestamps and re-sync)
- The private key is never stored client-side — only the public key is used for encryption; the server decrypts with its private key
