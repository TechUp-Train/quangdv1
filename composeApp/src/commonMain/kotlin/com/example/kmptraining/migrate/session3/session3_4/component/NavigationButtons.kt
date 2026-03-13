package com.example.kmptraining.migrate.session3.session3_4.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmptraining.migrate.session3.session3_4.FormAction
import com.example.kmptraining.migrate.session3.session3_4.FormState
import com.example.kmptraining.migrate.session3.session3_4.totalSteps

// ─── Navigation Buttons ───────────────────────────────────────────────────────
@Composable
fun FormNavigationButtons(
    state: FormState,
    onAction: (FormAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    // - Row(fillMaxWidth, spacedBy=12.dp, padding top=16.dp)
    // - Nếu currentStep > 0: OutlinedButton "Back" (weight(1f)) → onAction(PrevStep)
    // - Button "Next" hoặc "Submit" (weight(1f)) → onAction(NextStep) hoặc onAction(Submit)
    val isLastStep = state.currentStep == state.totalSteps - 1
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = strokeWidth,
                    )
                }.padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        OutlinedButton(
            onClick = { onAction(FormAction.PrevStep) },
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
        ) {
            if (state.currentStep == 0) {
                Text(
                    "Cancel",
                    style =
                        TextStyle(
                            fontSize = 16.sp,
                            color = Color.Gray,
                        ),
                )
            } else {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.Gray,
                    )
                    Text(
                        "Back",
                        style =
                            TextStyle(
                                fontSize = 16.sp,
                                color = Color.Gray,
                            ),
                    )
                }
            }
        }

        if (!isLastStep) {
            OutlinedButton(
                onClick = { onAction(FormAction.NextStep) },
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(vertical = 2.dp),
                colors =
                    ButtonColors(
                        containerColor = Color.Blue.copy(alpha = 0.6f),
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.LightGray,
                    ),
                modifier = Modifier.weight(1f),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Next step",
                        style =
                            TextStyle(
                                fontSize = 16.sp,
                            ),
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                    )
                }
            }
        } else {
            OutlinedButton(
                onClick = { onAction(FormAction.Submit) },
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(vertical = 2.dp),
                colors =
                    ButtonColors(
                        containerColor = Color.Magenta.copy(alpha = 0.2f),
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.LightGray,
                    ),
                modifier = Modifier.weight(1f),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                    )
                    Text(
                        "Submit",
                        style =
                            TextStyle(
                                fontSize = 16.sp,
                            ),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormNavigationButtonsPreview() {
    FormNavigationButtons(
        state =
            FormState(
                currentStep = 3,
            ),
        onAction = {},
    )
}
