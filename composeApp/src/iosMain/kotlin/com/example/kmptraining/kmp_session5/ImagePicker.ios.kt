package com.example.kmptraining.kmp_session5

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.kmptraining.kmp_session5.data.ImageModel
import platform.Foundation.NSUUID
import platform.Photos.PHPhotoLibrary
import platform.PhotosUI.PHPickerConfiguration
import platform.PhotosUI.PHPickerFilter
import platform.PhotosUI.PHPickerResult
import platform.PhotosUI.PHPickerViewController
import platform.PhotosUI.PHPickerViewControllerDelegateProtocol
import platform.UIKit.UIApplication
import platform.darwin.NSObject

actual class ImagePicker {

    @Composable
    actual fun launchGallery(
        allowMultiple: Boolean,
        maxItems: Int,
        onSelected: (List<ImageModel>) -> Unit,
        onDismiss: () -> Unit,
        onError: (String) -> Unit,
    ) {
        val configuration =
            PHPickerConfiguration(photoLibrary = PHPhotoLibrary.sharedPhotoLibrary()).apply {
                filter = PHPickerFilter.imagesFilter
                selectionLimit = if (allowMultiple) maxItems.toLong() else 1L
            }

        val picker = PHPickerViewController(configuration = configuration)

        val delegate = object : NSObject(), PHPickerViewControllerDelegateProtocol {

            override fun picker(
                picker: PHPickerViewController,
                didFinishPicking: List<*>
            ) {
                picker.dismissViewControllerAnimated(true, null)

                if (didFinishPicking.isEmpty()) {
                    onDismiss()
                    return
                }

                val selectedModels = mutableListOf<ImageModel>()

                var processedCount = 0
                didFinishPicking.forEachIndexed { index, resultAny ->
                    val result = resultAny as? PHPickerResult ?: run {
                        processedCount++
                        return@forEachIndexed
                    }

                    result.itemProvider.loadFileRepresentationForTypeIdentifier("public.image") { url, error ->
                        processedCount++

                        if (error != null) {
                            if (processedCount == didFinishPicking.size) finish(selectedModels)
                            return@loadFileRepresentationForTypeIdentifier
                        }

                        url?.let { nsUrl ->
                            val fileName =
                                nsUrl.lastPathComponent ?: "image_${NSUUID().UUIDString}.jpg"

                            selectedModels.add(
                                ImageModel(
                                    fileName = fileName,
                                    platformHandle = nsUrl
                                )
                            )
                        }

                        if (processedCount == didFinishPicking.size) {
                            finish(selectedModels)
                        }
                    }
                }
            }

            private fun finish(models: List<ImageModel>) {
                onSelected(models)
            }
        }

        picker.delegate = delegate

        UIApplication.sharedApplication.keyWindow?.rootViewController
            ?.presentViewController(picker, animated = true, completion = null)
    }
}

@Composable
actual fun rememberImagePicker(): ImagePicker = remember { ImagePicker() }