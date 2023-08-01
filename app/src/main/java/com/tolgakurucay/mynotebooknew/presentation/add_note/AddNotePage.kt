package com.tolgakurucay.mynotebooknew.presentation.add_note

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn

@Composable
fun AddNotePage(viewModel: AddNoteViewModel = hiltViewModel()){

    AddNoteContent(viewModel)

}
@Composable
private fun AddNoteContent(viewModel: AddNoteViewModel){

    BaseColumn(viewModel = viewModel) {

    }

}