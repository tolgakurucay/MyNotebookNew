package com.tolgakurucay.mynotebooknew.modules.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tolgakurucay.mynotebooknew.appstate.MyNotebookAppState
import com.tolgakurucay.mynotebooknew.appstate.rememberMyNotebookAppState


@Preview
@Composable
fun Register(
    viewModel: RegisterViewModel = viewModel(),
    appState: MyNotebookAppState = rememberMyNotebookAppState()
) {

    Surface(modifier = Modifier.fillMaxSize()) {
        RegisterContent()



    }


}


@Preview
@Composable
fun RegisterContent(){




}