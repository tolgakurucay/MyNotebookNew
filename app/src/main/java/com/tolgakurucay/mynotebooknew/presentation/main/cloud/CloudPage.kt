package com.tolgakurucay.mynotebooknew.presentation.main.cloud

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold

@Preview
@Composable
fun CloudPage(cloudViewModel: CloudViewModel = hiltViewModel()) {
    val state by cloudViewModel.state.collectAsStateWithLifecycle()

    CloudContent(state = state)

}

@Composable
fun CloudContent(state: CloudState) {
    BaseScaffold(state = state, topBar = { CloudTopBar() }){

        Column(modifier = Modifier.padding(it)) {

        }
    }
}