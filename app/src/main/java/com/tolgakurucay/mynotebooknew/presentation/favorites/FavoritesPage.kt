package com.tolgakurucay.mynotebooknew.presentation.favorites

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn

@Preview
@Composable
fun FavoritesPage(viewModel: FavoritesViewModel = hiltViewModel()){

    FavoritesContent(viewModel)

}

@Composable
private fun FavoritesContent(viewModel: FavoritesViewModel){

    BaseColumn(viewModel = viewModel) {

    }
}