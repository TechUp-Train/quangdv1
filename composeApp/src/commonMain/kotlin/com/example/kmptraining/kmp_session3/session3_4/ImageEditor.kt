package com.example.kmptraining.kmp_session3.session3_4

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.attafitamim.krop.core.crop.CropError
import com.attafitamim.krop.core.crop.CropResult
import com.attafitamim.krop.core.crop.crop
import com.attafitamim.krop.core.crop.rememberImageCropper
import com.attafitamim.krop.ui.ImageCropperDialog
import com.example.kmptraining.kmp_session3.session3_4.component.EditingBar
import dev.icerock.moko.media.compose.BindMediaPickerEffect
import dev.icerock.moko.media.compose.rememberMediaPickerControllerFactory
import dev.icerock.moko.media.compose.toImageBitmap
import dev.icerock.moko.media.picker.MediaSource
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageEditor() {
    val coroutineScope = rememberCoroutineScope()

    val mediaFactory = rememberMediaPickerControllerFactory()
    val mediaPicker = remember(mediaFactory) { mediaFactory.createMediaPickerController() }

    val permissionFactory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val permissionController: PermissionsController = remember(permissionFactory) {
        permissionFactory.createPermissionsController()
    }

    var image by remember { mutableStateOf<ImageBitmap?>(null) }

    val imageCropper = rememberImageCropper()
    val cropState = imageCropper.cropState

    BindEffect(permissionController)
    BindMediaPickerEffect(mediaPicker)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Image Editor") }
            )
        },
        bottomBar = {
            EditingBar(
                onCrop = {
                    image?.let { bitmap ->
                        coroutineScope.launch {
                            when (val result = imageCropper.crop(bitmap)) {

                                CropResult.Cancelled -> { }

                                is CropError -> { println("Crop error") }

                                is CropResult.Success -> {
                                    image = result.bitmap
                                }
                            }
                        }
                    }
                },
                onRotate = { },
                onFilter = { }
            )
        }
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val screenWidth = maxWidth
            val screenHeight = maxHeight
            Column(
                modifier = Modifier.matchParentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                image?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "Selected image",
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .width(screenWidth)
                            .height(screenHeight * 0.8f)
                            .padding(bottom = 20.dp),
                    )
                }

                cropState?.let {
                    ImageCropperDialog(state = it)
                }

                AnimatedVisibility(
                    visible = (image == null)
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                permissionController.providePermission(Permission.GALLERY)

                                val result = mediaPicker.pickImage(MediaSource.GALLERY)

                                result?.let {
                                    image = it.toImageBitmap()
                                }
                            }
                        }) {
                        Text(text = "Upload image")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ImageEditorPreview() {
    MaterialTheme {
        ImageEditor()
    }
}
