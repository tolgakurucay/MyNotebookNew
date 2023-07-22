package com.tolgakurucay.mynotebooknew.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Home(
    userName: String?,
    userId: Int?,
    viewModel: HomeViewModel = hiltViewModel()
) {
//    val uiState by viewModel.testResponse.collectAsStateWithLifecycle()

    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HomeContent(viewModel)
    }
}

@Composable
fun HomeContent(viewModel: HomeViewModel) {

    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Button(
            onClick = {
//                viewModel.getMainContent(2)

            },
        ) {

        }


    }


}
