package com.tolgakurucay.mynotebooknew.domain.base


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.presentation.custom.AlertDialogType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomAlertDialog
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomLoading

@Preview
@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier,
    state: BaseState = BaseState(),
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackBarHost: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = { }
) {
    val isShownLoading = state.isShowLoading.collectAsStateWithLifecycle()
    val isShownError = state.myNotebookException.collectAsStateWithLifecycle()


    Scaffold(
        modifier = modifier,
        content = content,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackBarHost
    )
    if (isShownLoading.value) {
        CustomLoading()
    }

    isShownError.value?.let { baseExc ->
        var message: String

        baseExc.exceptionType?.let {
            message = when (it) {
                ExceptionType.SIGNIN -> stringResource(id = R.string.error_signin)

                ExceptionType.CREATE_EMAIL_PASSWORD -> stringResource(id = R.string.error_register_email_password)

                ExceptionType.EMAIL_NOT_VERIFIED -> stringResource(id = R.string.error_email_not_verified)
            }

            CustomAlertDialog(
                type = AlertDialogType.OKAY, descriptionText = message,
                onConfirm = {
                    state.myNotebookException.value = null
                },
            )

        }
        baseExc.cause?.let {
            CustomAlertDialog(
                type = AlertDialogType.OKAY, descriptionText = it.localizedMessage,
                onConfirm = {
                    state.myNotebookException.value = null
                },
            )
        }


    }


}
