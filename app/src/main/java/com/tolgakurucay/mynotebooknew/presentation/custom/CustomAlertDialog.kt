package com.tolgakurucay.mynotebooknew.presentation.custom

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.presentation.theme.OnPrimaryLight
import com.tolgakurucay.mynotebooknew.presentation.theme.PrimaryLight
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing10

@Composable
fun CustomAlertDialog(
    type: AlertDialogType,
    @StringRes titleRes: Int = R.string.common_error,
    descriptionRes: String? = null,
    onConfirm: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
) {


    val showDialog = remember { mutableStateOf(true) }
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                //This runs when alertType is OKAY
                showDialog.value = false
                onConfirm?.invoke()
            },
            title = {
                Text(
                    text = stringResource(id = titleRes),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            text = {
                Text(
                    text = descriptionRes ?: run {
                        stringResource(id = R.string.common_an_error_occured)
                    },
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            confirmButton = {
                when (type) {
                    AlertDialogType.OKAY -> {
                        Text(
                            text = stringResource(id = R.string.common_okay),
                            modifier = Modifier
                                .clickable {
                                    onConfirm?.invoke()
                                    showDialog.value = false
                                }
                                .padding(horizontal = spacing10),
                        )
                    }

                    AlertDialogType.YES_OR_NO -> {
                        Text(
                            text = stringResource(id = R.string.common_yes),
                            modifier = Modifier
                                .clickable {
                                    onConfirm?.invoke()
                                    showDialog.value = false
                                }
                                .padding(horizontal = spacing10),
                        )

                    }
                }
            },
            dismissButton = {
                when (type) {
                    AlertDialogType.OKAY -> {
                        //Do nothing
                    }

                    AlertDialogType.YES_OR_NO -> {
                        Text(
                            text = stringResource(id = R.string.common_no),
                            modifier = Modifier
                                .clickable {
                                    onDismiss?.invoke()
                                    showDialog.value = false
                                }
                                .padding(horizontal = spacing10),
                        )
                    }
                }
            },
            properties =
            when (type) {
                AlertDialogType.OKAY -> {
                    DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
                }

                AlertDialogType.YES_OR_NO -> {
                    DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                }
            }


        )

    }


}


/**
 * If you selected OKAY;
 * Invoke the onConfirm button
 *
 * Else if you selected YES_OR_NO
 * Invoke the onConfirm and onDismiss button
 */

enum class AlertDialogType {
    OKAY,
    YES_OR_NO
}

