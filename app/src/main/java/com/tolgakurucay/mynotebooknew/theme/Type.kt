package com.tolgakurucay.mynotebooknew.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tolgakurucay.mynotebooknew.R

//Implementation Example
//https://developer.android.com/jetpack/compose/designsystems/material3

private val Comfortaa = FontFamily(
    Font(R.font.comfortaa_light, FontWeight.Light),
    Font(R.font.comfortaa_regular, FontWeight.Normal),
    Font(R.font.comfortaa_medium, FontWeight.Medium),
    Font(R.font.comfortaa_bold, FontWeight.Bold),
    Font(R.font.comfortaa_semibold, FontWeight.SemiBold)
)

val MyNotebookNewTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize57,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize64
    ),
    displayMedium = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize45,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize52
    ),
    displaySmall = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize36,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize44
    ),
    headlineLarge = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize32,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize40
    ),
    headlineMedium = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize28,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize36
    ),
    headlineSmall = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize24,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize32
    ),
    titleLarge = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize22,
        fontWeight = FontWeight.Medium,
        lineHeight = fontSize28
    ),
    titleMedium = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize16,
        fontWeight = FontWeight.Medium,
        lineHeight = fontSize24
    ),
    titleSmall = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize14,
        fontWeight = FontWeight.Medium,
        lineHeight = fontSize20
    ),
    bodyLarge = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize16,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize24
    ),
    bodyMedium = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize14,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize20
    ),
    bodySmall = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize12,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize16
    ),
    labelLarge = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize14,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize20
    ),
    labelMedium = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize12,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize16
    ),
    labelSmall = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize11,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize16
    ),)
