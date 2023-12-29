package com.tolgakurucay.mynotebooknew.presentation.main.profile.who_am_i

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.dp
import com.tolgakurucay.mynotebooknew.presentation.theme.Comfortaa

@Composable
fun WhoAmIText(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .padding(start = 16.dp),
        style = TextStyle(
            shadow = Shadow(
                color = Color.Blue,
                offset = Offset(5.0f, 10f),
                blurRadius = 5f
            ),
            textMotion = TextMotion.Animated,
            fontFamily = Comfortaa,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        ),
    )
}