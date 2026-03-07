package com.example.composetraining.session3.session3_4.component.contactStep

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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.R
import com.example.composetraining.common.h
import com.example.composetraining.session3.session3_4.FormAction
import com.example.composetraining.session3.session3_4.FormState
import com.example.composetraining.session3.session3_4.component.ValidatedTextField
import com.example.composetraining.ui.theme.ComposeTrainingTheme

// ─── Step 2: Contact ──────────────────────────────────────────────────────────

@Composable
fun ContactStep(
    state: FormState,
    onAction: (FormAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    // - Column(fillMaxSize, verticalScroll, spacedBy=12.dp)
    // - ValidatedTextField email (error = emailError, keyboardType = Email)
    // - ValidatedTextField phone (error = phoneError, keyboardType = Phone)
    // - OutlinedTextField city (optional)
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            stringResource(R.string.contact_details),
            style = TextStyle(
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
        )
        Text(
            stringResource(R.string.contact_details_description),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
            )
        )
        Spacer(Modifier.height(2.h))
        ValidatedTextField(
            value = state.email,
            onValueChange = { onAction(FormAction.UpdateEmail(it)) },
            label = stringResource(R.string.email),
            errorMessage = state.emailError,
            keyboardType = KeyboardType.Email,
            icon = { Icon(imageVector = Icons.Default.MailOutline, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
        ValidatedTextField(
            value = state.phone,
            onValueChange = { onAction(FormAction.UpdatePhone(it)) },
            label = stringResource(R.string.phone),
            errorMessage = state.phoneError,
            keyboardType = KeyboardType.Number,
            icon = { Icon(imageVector = Icons.Default.Call, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()

        )
        ValidatedTextField(
            value = state.city,
            onValueChange = { onAction(FormAction.UpdateCity(it)) },
            label = stringResource(R.string.city),
            keyboardType = KeyboardType.Text,
            icon = { Icon(imageVector = Icons.Default.Place, contentDescription = null) },
            errorMessage = null,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactStepPreview() {
    ComposeTrainingTheme {
        Surface {
            ContactStep(
                state = FormState(
                    email = "example@email.com",
                    phone = "0123456789",
                    city = "Hanoi"
                ),
                onAction = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactStepErrorPreview() {
    ComposeTrainingTheme {
        Surface {
            ContactStep(
                state = FormState(
                    email = "invalid-email",
                    emailError = "Invalid email format",
                    phone = "123",
                    phoneError = "Phone must be 10 digits"
                ),
                onAction = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}