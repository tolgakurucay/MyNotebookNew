package com.tolgakurucay.mynotebooknew.modules.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tolgakurucay.mynotebooknew.services.TestResponse

@Composable
fun Home(
    userName: String?,
    userId: Int?,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.testResponse.collectAsStateWithLifecycle()

    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HomeContent(viewModel, uiState)
    }
}

@Composable
fun HomeContent(viewModel: HomeViewModel, value: TestResponse?) {

    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Button(
            onClick = {
                viewModel.getMainContent(2)

            },
        ) {

        }


    }


}
