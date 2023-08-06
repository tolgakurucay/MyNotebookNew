package com.tolgakurucay.mynotebooknew.domain.base


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.presentation.custom.AlertDialogType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomAlertDialog
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomLoading


@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier,
    viewModel: BaseViewModel = hiltViewModel(),
    topBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {  }
) {
    val isShownLoading = viewModel.isShowLoading.collectAsStateWithLifecycle()
    val isShownError = viewModel.myNotebookException.collectAsStateWithLifecycle()


    Scaffold(modifier = modifier, content = content, topBar = topBar)
    if (isShownLoading.value == true) {
        CustomLoading()
    }

    isShownError.value?.let { baseExc ->
        var message: String

        baseExc.exceptionType?.let {
            message = when (it) {
                ExceptionType.SIGNIN -> {
                    stringResource(id = R.string.error_signin)
                }

                ExceptionType.CREATE_EMAIL_PASSWORD -> {
                    stringResource(id = R.string.error_register_email_password)
                }

                ExceptionType.EMAIL_NOT_VERIFIED -> {
                    stringResource(id = R.string.error_email_not_verified)
                }
            }

            CustomAlertDialog(
                type = AlertDialogType.OKAY, descriptionRes = message,
                onConfirm = {
                    viewModel.myNotebookException.value = null
                },
            )

        } ?: kotlin.run {
            CustomAlertDialog(
                type = AlertDialogType.OKAY,
                descriptionRes = baseExc.cause?.localizedMessage ?: stringResource(
                    id = R.string.common_error
                ),
                onConfirm = {
                    viewModel.myNotebookException.value = null
                },
            )
        }


    }


}

@Composable
@Preview
fun PreviewScaffold() {
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