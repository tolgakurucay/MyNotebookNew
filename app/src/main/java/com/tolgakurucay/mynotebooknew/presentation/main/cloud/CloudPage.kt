package com.tolgakurucay.mynotebooknew.presentation.main.cloud

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn

@Preview
@Composable
fun CloudPage(cloudViewModel: CloudViewModel = hiltViewModel()) {

    CloudContent(state = cloudViewModel.state.value)


}

@Composable
fun CloudContent(state: CloudState) {
    BaseColumn(state = state) {


    }
}