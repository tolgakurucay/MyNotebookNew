package com.tolgakurucay.mynotebooknew.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.base.BaseViewModel

private val TAG = "bilgitolga"

@Composable
fun BaseColumn(
    modifier: Modifier = Modifier,
    viewModel: BaseViewModel = hiltViewModel(),
    content: @Composable ColumnScope.() -> Unit
) {
    val isShownLoading = viewModel.isShowLoading.collectAsStateWithLifecycle()
    val isShownError = viewModel.myNotebookError.collectAsStateWithLifecycle()


    Box(contentAlignment = Alignment.Center) {
        Column(modifier = modifier, content = content)
        if (isShownLoading.value == true) {
            CustomLoading()
        }
        if (isShownError.value!=null) {
            CustomAlertDialog(
                type = AlertDialogType.OKAY, descriptionRes = isShownError.value?.message,
                onConfirm = {
                    viewModel.myNotebookError.value = null
                },
            )
        }

    }


}

@Composable
@Preview
fun Preview() {
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Red)
        ) {

        }
        CustomLoading()
    }
}