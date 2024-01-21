package com.tolgakurucay.mynotebooknew.presentation.main.profile.main

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.tolgakurucay.mynotebooknew.R

@Preview
@Composable
fun ProfileTabItem( onClick: () -> Unit = {}) {
    val selectedImage = rememberAsyncImagePainter(
        ContextCompat.getDrawable(
            LocalContext.current,
            R.drawable.ic_selected_dot
        )
    )
   /* val notSelectedImage = rememberAsyncImagePainter(
        ContextCompat.getDrawable(
            LocalContext.current,
            R.drawable.ic_not_selected_dot
        )
    )*/
    Icon(
        painter = selectedImage,
        contentDescription = ".",
        modifier = Modifier.clickable {
            onClick.invoke()
        }
    )
}