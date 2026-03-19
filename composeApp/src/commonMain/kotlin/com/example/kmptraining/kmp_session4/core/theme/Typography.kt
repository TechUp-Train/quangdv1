package com.example.kmptraining.kmp_session4.core.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.inter_18pt_bold
import kmptraining.composeapp.generated.resources.inter_18pt_medium
import kmptraining.composeapp.generated.resources.inter_18pt_regular
import org.jetbrains.compose.resources.Font

@Composable
fun InterFont() = FontFamily(
    Font(Res.font.inter_18pt_regular, weight = FontWeight.Normal),
    Font(Res.font.inter_18pt_medium, weight = FontWeight.Medium),
    Font(Res.font.inter_18pt_bold, weight = FontWeight.Bold),
)

@Composable
fun AppTypography() = Typography().run {
    val fontFamily = InterFont()
    copy(

        headlineLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp
        ),

        headlineMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        ),

        titleLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        ),

        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),

        bodyMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),

        labelSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        ),

        labelLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
    )
}