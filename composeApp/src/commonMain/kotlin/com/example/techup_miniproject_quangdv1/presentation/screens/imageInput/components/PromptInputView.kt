package com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.techup_miniproject_quangdv1.core.theme.BrandMagenta

@Composable
fun PromptInputView(
    prompt: String,
    onPromptChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {

        TextField(
            value = prompt,
            onValueChange = onPromptChange,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .matchParentSize()
                .border(
                    width = 1.5.dp,
                    color = BrandMagenta,
                    shape = RoundedCornerShape(16.dp)
                ),

            placeholder = {
                Text(
                    text = "Enter your prompt...",
                    color = Color.Black.copy(alpha = 0.4f)
                )
            },

            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedContainerColor = Color(0xFFF5F5F5),

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,

                cursorColor = BrandMagenta
            ),

            singleLine = false,
            maxLines = 4
        )

        if (prompt.isNotEmpty()) {
            IconButton(
                onClick = { onPromptChange("") },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clear",
                    tint = Color.Black.copy(alpha = 0.5f),
                    modifier = Modifier
                        .size(25.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.Black.copy(alpha = 0.1f))
                        .padding(5.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PromptInputViewPreview() {
    PromptInputView(prompt = "Test" +
            "asudhblaiusdns" +
            "als;kdnmoaislk;fd" +
            "\nauisdhiasdjas" +
            "\naisudhalskjdjasoil", onPromptChange = {})
}