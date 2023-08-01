package com.tolgakurucay.mynotebooknew.presentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn

@Preview
@Composable
fun ProfilePage(viewModel: ProfileViewModel = hiltViewModel()){

    ProfileContent(viewModel)

}



@Composable
fun ProfileContent(viewModel: ProfileViewModel){

    BaseColumn(viewModel = viewModel) {

    }
}