package com.tolgakurucay.mynotebooknew.domain.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.presentation.custom.AlertDialogType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomAlertDialog
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomLottieLoading


@Composable
fun BaseColumn(
    modifier: Modifier = Modifier,
    state: BaseState,
    content: @Composable ColumnScope.() -> Unit
) {

    val isShownLoading = state.isShowLoading.collectAsStateWithLifecycle()
    val isShownError = state.myNotebookException.collectAsStateWithLifecycle()


    Box(contentAlignment = Alignment.Center) {

        if (isShownLoading.value) CustomLottieLoading()

        Column(modifier = modifier, content = content)

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


}
