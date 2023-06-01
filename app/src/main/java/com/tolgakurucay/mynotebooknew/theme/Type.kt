package com.tolgakurucay.mynotebooknew.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tolgakurucay.mynotebooknew.R


private val Comfortaa = FontFamily(
    Font(R.font.comfortaa_light, FontWeight.Light),
    Font(R.font.comfortaa_regular, FontWeight.Normal),
    Font(R.font.comfortaa_medium, FontWeight.Medium),
    Font(R.font.comfortaa_bold, FontWeight.Bold),
    Font(R.font.comfortaa_semibold, FontWeight.SemiBold)
)

val MyNotebookNewTypography = Typography(
    titleSmall = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize18,
        fontWeight = FontWeight.Medium,
    ),
    titleMedium = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize20,
        fontWeight = FontWeight.Bold,
    ),
    titleLarge = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize22,
        fontWeight = FontWeight.SemiBold,
    ),
    bodySmall = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize10,
        fontWeight = FontWeight.Light,
    ),
    bodyMedium = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize14,
        fontWeight = FontWeight.Medium,
    ),
    bodyLarge = TextStyle(
        fontFamily = Comfortaa,
        fontSize = fontSize24,
        fontWeight = FontWeight.Bold,
    )

)
