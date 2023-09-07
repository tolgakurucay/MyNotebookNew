package com.tolgakurucay.mynotebooknew.presentation.main.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn

@Preview
@Composable
fun ProfilePage(viewModel: ProfileViewModel = hiltViewModel()){

    ProfileContent(viewModel.state.value)

}



@Composable
fun ProfileContent(state : ProfileState){

    BaseColumn(state = state) {

    }
}