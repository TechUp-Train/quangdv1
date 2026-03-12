package com.example.kmptraining.migrate.session3.session3_4.component.persionalInfoStep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmptraining.migrate.session3.session3_4.FormAction
import com.example.kmptraining.migrate.session3.session3_4.FormState
import com.example.kmptraining.migrate.session3.session3_4.component.ValidatedTextField
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.birth_year
import kmptraining.composeapp.generated.resources.first_name
import kmptraining.composeapp.generated.resources.last_name
import kmptraining.composeapp.generated.resources.personal_info
import kmptraining.composeapp.generated.resources.personal_info_description
import org.jetbrains.compose.resources.stringResource

// ─── Step 1: Personal Info ────────────────────────────────────────────────────
@Composable
fun PersonalInfoStep(
    state: FormState,
    onAction: (FormAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    // - Column(fillMaxSize, verticalScroll, spacedBy=12.dp)
    // - ValidatedTextField firstName (error = state.firstNameError)
    // - ValidatedTextField lastName (error = state.lastNameError)
    // - OutlinedTextField birthYear (optional, keyboardType = Number)
    val scrollState = rememberScrollState()

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            stringResource(Res.string.personal_info),
            style =
                TextStyle(
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                ),
        )
        Text(
            stringResource(Res.string.personal_info_description),
            style =
                TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                ),
        )
        Spacer(Modifier.height(2.dp))
        ValidatedTextField(
            value = state.firstName,
            onValueChange = { onAction(FormAction.UpdateFirstName(it)) },
            label = stringResource(Res.string.first_name),
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.Black,
                )
            },
            errorMessage = state.firstNameError,
            modifier = Modifier.fillMaxWidth(),
        )
        ValidatedTextField(
            value = state.lastName,
            onValueChange = { onAction(FormAction.UpdateLastName(it)) },
            label = stringResource(Res.string.last_name),
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.Black,
                )
            },
            errorMessage = state.lastNameError,
            modifier = Modifier.fillMaxWidth(),
        )
        ValidatedTextField(
            value = state.birthYear,
            onValueChange = { onAction(FormAction.UpdateBirthYear(it)) },
            label = stringResource(Res.string.birth_year),
            keyboardType = KeyboardType.Number,
            trailingIcon = {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
            },
            errorMessage = null,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonalInfoStepPreview() {
    ComposeTrainingTheme {
        Scaffold { padding ->
            PersonalInfoStep(
                state =
                    FormState(
                        firstName = "John",
                        lastName = "Doe",
                        birthYear = "1990",
                    ),
                onAction = {},
                modifier =
                    Modifier
                        .padding(padding)
                        .padding(16.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonalInfoStepErrorPreview() {
    ComposeTrainingTheme {
        Scaffold { padding ->
            PersonalInfoStep(
                state =
                    FormState(
                        firstName = "John",
                        lastName = "Doe",
                        birthYear = "1990",
                        firstNameError = "Invalid First name",
                        lastNameError = "Invalid Last name",
                    ),
                onAction = {},
                modifier =
                    Modifier
                        .padding(padding)
                        .padding(16.dp),
            )
        }
    }
}
