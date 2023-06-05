package com.tolgakurucay.mynotebooknew.modules.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
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
    viewModel: HomeViewModel = hiltViewModel()
) {
    val value by viewModel.testResponse.collectAsStateWithLifecycle()
    Surface(Modifier.fillMaxSize()) {
        HomeContent(viewModel,value)
    }
}

@Composable
fun HomeContent(viewModel: HomeViewModel,value : TestResponse?) {

    Column(modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.systemBars)) {
        Button(onClick = {
            viewModel.getMainContent(1)

        }) {

        }
        
        
        Text(text = value?.results?.get(1)?.content.toString())
    }
    value?.let {


    }


}
