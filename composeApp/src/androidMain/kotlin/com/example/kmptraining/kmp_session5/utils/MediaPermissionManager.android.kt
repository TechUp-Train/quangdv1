package com.example.kmptraining.kmp_session5.utils

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@Composable
actual fun rememberMediaPermissionManager(): MediaPermissionManager {
    var continuation by remember { mutableStateOf<((Boolean) -> Unit)?>(null) }

    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        continuation?.invoke(granted)
        continuation = null
    }

    return remember {
        AndroidPermissionManager(
            requestPermission = {
                suspendCancellableCoroutine { cont ->
                    continuation = { granted ->
                        cont.resume(granted)
                    }
                    launcher.launch(permission)
                }
            }
        )
    }
}