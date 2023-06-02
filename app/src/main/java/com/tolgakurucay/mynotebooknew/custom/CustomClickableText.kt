package com.tolgakurucay.mynotebooknew.custom

import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle


@Composable
fun CustomClickableText(@StringRes stringResId: Int, modifier: Modifier, onClick: () -> Unit) {
    ClickableText(
        text = AnnotatedString(stringResource(id = stringResId)),
        modifier = modifier,
        style =
        if (isSystemInDarkTheme()) {
            TextStyle(color = Color.White)
        } else {
            MaterialTheme.typography.bodyMedium
        },
        onClick = {
            onClick.invoke()
        }
    )


}

