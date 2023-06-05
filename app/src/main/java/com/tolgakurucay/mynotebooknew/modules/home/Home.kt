package com.tolgakurucay.mynotebooknew.modules.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview
@Composable
fun Home(
    viewModel: HomeViewModel = viewModel()
) {

    Surface(Modifier.fillMaxSize()) {
        HomeContent()
    }
}

@Composable
fun HomeContent() {
    Text(text = "öksdjnfökjn")


}
