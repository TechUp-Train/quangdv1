package com.example.techup_miniproject_quangdv1.presentation.screens.imageInput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.techup_miniproject_quangdv1.core.utils.ImageMode
import com.example.techup_miniproject_quangdv1.core.utils.ResponseStatus
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components.PromptInputView
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components.StylesListView
import com.example.techup_miniproject_quangdv1.presentation.sharedComponents.RoundedImageFrame
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ImageInputScreen(onSelectImages: (ImageMode) -> Unit, viewModel: ImageInputViewModel = koinViewModel()) {
    val promptText = remember { mutableStateOf("") }
    val imageMode = remember { mutableStateOf(ImageMode.FIGURE_MAKER) }

    Scaffold { contentPadding ->
        val stylesState = viewModel.stylesState.collectAsStateWithLifecycle().value

        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
                .padding(contentPadding)
        ) {
            val screenHeight = maxHeight
            Column(
                modifier = Modifier.padding(horizontal = 5.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                PromptInputView(
                    modifier = Modifier.height(screenHeight * 0.15f),
                    prompt = promptText.value,
                    onPromptChange = { newPrompt ->
                        promptText.value = newPrompt
                    },
                )
                Spacer(modifier = Modifier.height(10.dp))
                RoundedImageFrame(
                    modifier = Modifier.height(screenHeight * 0.5f),
                    onChangeImage = { onSelectImages(imageMode.value) }
                )

                if (stylesState is ResponseStatus.Success) {
                    StylesListView(
                        categories = stylesState.data,
                        selectedCategoryIndex = 0,
                        selectedStyleId = null,
                        onCategorySelected = {},
                        onStyleSelected = {},
                    )
                }

            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun ImageInputScreenPreview() {
    ImageInputScreen({})
}