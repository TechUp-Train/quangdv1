package com.example.composetraining.session3.session3_4.component.preferenceStep

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.R
import com.example.composetraining.common.h
import com.example.composetraining.session3.session3_4.FormAction
import com.example.composetraining.session3.session3_4.FormState
import com.example.composetraining.ui.theme.ComposeTrainingTheme

// ─── Step 3: Preferences ─────────────────────────────────────────────────────

@Composable
fun PreferencesStep(
    state: FormState,
    onAction: (FormAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement PreferencesStep
    // - Column(fillMaxSize, verticalScroll, spacedBy=16.dp)
    // - SwitchRow "Receive newsletter" (receiveNewsletter)
    // - SwitchRow "Push notifications" (receiveNotifications)
    // - HorizontalDivider
    // - Text "Preferred language"
    // - listOf("Vietnamese", "English", "Japanese", "Korean").forEach { lang →
    //     Row: RadioButton(selected = state.preferredLanguage == lang, onClick = ...) + Text lang
    //   }
    val scrollState = rememberScrollState()
    val languages = listOf("Vietnamese", "English", "Japanese", "Korean")

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            stringResource(R.string.preferences),
            style = TextStyle(
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
        )
        Text(
            stringResource(R.string.preferences_description),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
            )
        )
        Spacer(Modifier.height(2.h))
        SwitchRow(
            label = "Receive newsletter",
            description = "Get weekly updates and tips",
            icon = RowIcon(
                icon = Icons.Default.Email,
                color = Color.Blue,
            ),
            checked = state.receiveNewsletter,
            onCheckedChange = { enabled -> onAction(FormAction.UpdateNewsletter(enabled)) },
        )
        SwitchRow(
            label = "Push notifications",
            description = "Stay updated with real-time updates",
            icon = RowIcon(
                icon = Icons.Default.Notifications,
                color = Color.Magenta,
            ),
            checked = state.receiveNotifications,
            onCheckedChange = { enabled -> onAction(FormAction.UpdateNotifications(enabled)) },
        )
        HorizontalDivider()
        Text("Preferred language")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(0.dp))
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            languages.forEachIndexed { index, lang ->
                LanguageItem(
                    language = lang,
                    isSelected = state.preferredLanguage == lang,
                    onSelect = { onAction(FormAction.UpdateLanguage(lang)) },
                    modifier = Modifier.fillMaxWidth()
                )
                if (index < languages.lastIndex)
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(PaddingValues(0.dp))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreferencesStepPreview() {
    ComposeTrainingTheme {
        PreferencesStep(
            state = FormState(
                receiveNewsletter = true,
                receiveNotifications = false,
                preferredLanguage = "English"
            ),
            onAction = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}